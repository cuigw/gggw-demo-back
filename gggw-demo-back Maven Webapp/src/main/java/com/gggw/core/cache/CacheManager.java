package com.gggw.core.cache;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.gggw.core.utils.SpringContext;
import com.gggw.util.EnvironmentUtils;
import com.gggw.util.FastJsonUtil;
import com.gggw.util.HttpClientUtil;
import com.gggw.util.PropertiesUtils;
import com.gggw.util.RequestUtil;
import com.gggw.util.WebServerUtil;
import com.gggw.util.jedis.RedisClientUtil;

/**
 * ClassName:CacheManager <br/>
 * Function: 缓存管理器. <br/>
 * Date:     2016-10-19 下午3:37:28 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Lazy(false)
@Component("cacheManager")
public class CacheManager {

	private final Logger logger = LoggerFactory.getLogger(CacheManager.class);
	
	public static final String CACHE_CLIENTS_KEY = "config.cache.clients";
	private static final String CACHE_CONFIG_KEY = "config.cache.";
	
	private static final int REFRESH_SLEEP_TIME = 30 * 60 * 1000;
	
	@Autowired
	private ApplicationContext context;
	@Autowired
	private SpringContext springContext;
	
	// 全局变量，只允许在init中赋值一次
	private String service_address;
	private static int wait_times = 0;
	
	@PostConstruct
	private void init() throws Exception {
		if (context instanceof WebApplicationContext) {
			service_address = WebServerUtil.getServiceAddress(context);
		}
		registerCacheTerminal();

		loadAndStartAutotask();
	}
	
	//注册缓存调用者终端
	private void registerCacheTerminal() {
		Map<String, Boolean> clients = getRegisterClients();
		if (StringUtils.contains(service_address, "xpe-context-config")) {
			if (!Boolean.TRUE.equals(clients.get(service_address))) {
				if (!clients.containsValue(Boolean.TRUE)) {
					clients.put(service_address, true);
				}else{
					for (Entry<String, Boolean> entry : clients.entrySet()) {
						if (!entry.getValue()) {
							continue;
						}
						try {
							HttpClientUtil httpclient = HttpClientUtil.getInstance();
							httpclient.setTimeout(30 * 1000);// 30s
							httpclient.httpGet(entry.getKey(), null);
							clients.put(service_address, false);
						} catch (Exception e) {
							clients.remove(entry.getKey());
							clients.put(service_address, true);
						}
						break;
					}
				}
			}
		} else {
			clients.put(service_address, false);
		}
		RedisClientUtil.set(CACHE_CLIENTS_KEY, FastJsonUtil.toJSONString(clients), REFRESH_SLEEP_TIME * 2);
	}
	
	//加载配置，并启动轮询，自动重新注册
	private void loadAndStartAutotask() {
		new Thread() {

			public void run() {
				while (true) {
					Map<String, Boolean> clients = getRegisterClients();
					if (Boolean.TRUE.equals(clients.get(service_address))) {
						loadAllFilesToRedis();
					} else if (clients.containsKey(service_address)) {
						// 这里会执行重新注册，以防网络问题某次通知失败时被误清
						registerCacheTerminal();
					}

					refreshAllCache();
					try {
						// 每隔30分钟刷新
						Thread.sleep(REFRESH_SLEEP_TIME);
					} catch (InterruptedException e) {
						logger.error("线程休眠发生错误", e);
					}
				}
			}

		}.start();
	}
	
	/**
	 * 功能说明: 关闭时清除缓存<br>
	 */
	@PreDestroy
	private void destroy() {
		Map<String, Boolean> clients = getRegisterClients();
		clients.remove(service_address);
		RedisClientUtil.set(CACHE_CLIENTS_KEY, FastJsonUtil.toJSONString(clients), REFRESH_SLEEP_TIME * 2);
	}

	//==============================================      toolFunction         =========================================//
	
	//获取注册缓存客户端列表，并注册当前客户端
	private Map<String, Boolean> getRegisterClients() {
		String jsonStr = RedisClientUtil.get(CACHE_CLIENTS_KEY);
		Map<String, Boolean> map = JSON.parseObject(jsonStr, Map.class);
		if (map == null) {
			map = new HashMap<String, Boolean>();
		}
		return map;
	}
	
	//加载配置文件目录下所有的配置文件
	private void loadAllFilesToRedis() {
		File runtimeDir = new File(EnvironmentUtils.getRuntimeConfigPath());
		for (File file : FileUtils.listFiles(runtimeDir, new String[] {"properties", "xml"}, true)) {
			String fileName = file.getAbsolutePath().replace(runtimeDir.getAbsolutePath(), "").replace(File.separator, "/");
			loadOneFileToRedis(fileName);
		}
	}
	
	//加载一个文件
	public void loadOneFileToRedis(String fileName) {
		Properties prop = new Properties();
		try {
			File file = new File(EnvironmentUtils.getFileAbsolutePath(fileName));
			prop.load(new FileInputStream(file));
			RedisClientUtil.set(CACHE_CONFIG_KEY + fileName, FastJsonUtil.toJSONString(prop));
		} catch (Exception e) {
			logger.error(String.format("读取配置文件[%s]发生错误", fileName), e);
		}
	}
	
	private void refreshAllCache() {
		/*
		 * 根据order顺序刷新，先刷新被引用项
		 */
		List<Map.Entry<String, ICache>> list = springContext.getOrderedBeans(ICache.class);
		if (list == null || list.isEmpty()) {
			if (wait_times > 3) {
				throw new RuntimeException("context初始化不正常，请检查配置");
			}
			logger.info("context未初始化，等待10秒加载缓存");
			try {
				Thread.sleep(10 * 1000);// context未初始化，等待10秒
			} catch (InterruptedException e) {
				logger.error("线程休眠发生错误", e);
			}
			wait_times++;
			refreshAllCache();
		}
		wait_times = 0;
		for (Map.Entry<String, ICache> entry : list) {
			try {
				refreshOne(entry.getValue().getId());
			} catch (Exception e) {
				logger.error("更新缓存发生错误", e);
			}
		}
	}
	
	public boolean refreshOne(String cacheName) {
		ICache<?> cache = findCacheBeanByName(cacheName);

		boolean isMain = false;
		HttpServletRequest request = RequestUtil.getRequest();
		if (request != null && !request.getRequestURI().contains("/cache/refresh.json")) {
			isMain = true;
		}
		try {
			if (cache instanceof PropertiesCache) {
				cacheName = cache.getId();
				PropertiesCache pcache = (PropertiesCache)cache;
				Map map = pcache.getConfigData();
				map.clear();
				for (String file : pcache.getPropertiesNames()) {
					if (isMain) {
						loadOneFileToRedis(file);
					}
					String jsonStr = RedisClientUtil.get(CACHE_CONFIG_KEY + file);
					Properties prop = JSON.parseObject(jsonStr, Properties.class);
					if (prop != null) {
						map.putAll(prop);
					}
				}
				// 更新Properties文件，清除老配置重新加载
				PropertiesUtils.clear();
			} else {
				if(cache != null) {
					cache.refresh();
				}
			}
			if (isMain) {
				callOthersRefresh(cacheName, service_address);
			} 
			logger.info("刷新缓存[" + cacheName + "]成功！");
			return true;
		} catch (Exception e) {
			logger.error("刷新缓存[" + cacheName + "]发生错误！", e);
			return false;
		}

	}
	
	/**
	 * 按cache名称或文件名称取缓存bean
	 * @param cacheName
	 * @return
	 */
	private ICache findCacheBeanByName(String cacheName) {
		Map<String, ICache> beans = SpringContext.getBeansOfType(ICache.class);
		for (Map.Entry<String, ICache> entry : beans.entrySet()) {
			if (cacheName.equals(entry.getValue().getId())) {
				return entry.getValue();
			}
		}
		for (Map.Entry<String, PropertiesCache> entry : SpringContext.getBeansOfType(PropertiesCache.class).entrySet()) {
			if (ArrayUtils.contains(entry.getValue().getPropertiesNames(), cacheName)) {
				return entry.getValue();
			}
		}
		return null;
	}
	
	/**
	 * 通知其他服务器刷新缓存
	 * @param cacheName
	 * @param address
	 * @param clients
	 */
	private void callOthersRefresh(final String cacheName, final String address) {
		Map<String, Boolean> clients = this.getRegisterClients();
		if (StringUtils.contains(service_address, "xpe-context-config")) {
			clients.put(service_address, true);
			RedisClientUtil.set(CACHE_CLIENTS_KEY, FastJsonUtil.toJSONString(clients), REFRESH_SLEEP_TIME * 2);
		}
		for (final String addr : clients.keySet()) {
			if (StringUtils.equals(address, addr)) {
				continue;
			}
			new Thread() {

				public void run() {
					try {
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("name", cacheName);
						byte[] resp = HttpClientUtil.getInstance().httpPost(addr + "/cache/refresh.json", params, null);
						logger.info(String.format("通知注册服务器[%s]刷新缓存：%s", addr, new String(resp)));
					} catch (Exception e) {
						logger.error("通知注册服务器刷新缓存发生错误，移除此注册地址", e);
						removeUnreachable(addr);
					}
				}
			}.start();
		}
	}
	
	/**
	 * 移除不可达的注册地址
	 * @param address
	 */
	private void removeUnreachable(String address) {
		String jsonStr = RedisClientUtil.get(CACHE_CLIENTS_KEY);
		Map<String, Boolean> map = JSON.parseObject(jsonStr, Map.class);
		if (map != null) {
			map.remove(address);
			RedisClientUtil.set(CACHE_CLIENTS_KEY, FastJsonUtil.toJSONString(map), REFRESH_SLEEP_TIME * 2);
		}
	}
}

