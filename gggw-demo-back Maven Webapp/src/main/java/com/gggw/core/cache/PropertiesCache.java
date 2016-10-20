package com.gggw.core.cache;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gggw.core.utils.EnvironmentUtils;

/**
 * ClassName:PropertiesCache <br/>
 * Function: properties文件的统一缓存基类. <br/>
 * Date:     2016-10-19 下午3:51:32 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public abstract class PropertiesCache implements ICache<Properties> {
	
	private final Logger logger = LoggerFactory.getLogger(PropertiesCache.class);
	
	//extends Hashtable<Object,Object>
	private Properties config = new Properties();
	
	//配置文件率先加载
	@Override
	public int getOrder() {
		return -100;
	}
	
	@Override
	public synchronized void refresh() throws Exception {
		config = new Properties();
		String[] filenames = getPropertiesNames();
		boolean loadone = false;
		for (int i = 0; i < filenames.length; i++) {
			InputStream configIs = null;
			try {
				// 以“/”跟路径方式拼装配置文件路径，EnvironmentUtils工具类要求
				String filePath = EnvironmentUtils.getFileAbsolutePath(String.format("/%s", filenames[i]));
				configIs = new FileInputStream(filePath);
				config.load(configIs);
				loadone = true;
				logger.debug("PropertiesCache with filename: {} and data : {} ", filenames[i], config);
			} catch (Exception e) {
				if (!loadone && i + 1 == filenames.length) {
					throw e;
				}
				logger.error(String.format("未找到配置文件[%s]", filenames[i]), e);
			} finally {
				if (configIs != null) {
					try {
						configIs.close();
					} catch (IOException e) {
						logger.error("关闭文件流错误", e);
					}
				}
			}
		}
	}
	
	@Override
	public Map getConfigData() {
		return config;
	}
	
	public void setConfigData(Properties config) {
		this.config = config;
	}
	
	public String getConfigValue(String config_key) {
		if (this.config != null) {
			return (String)getConfigData().get(config_key);
		}
		return null;
	}	
	
	public abstract String[] getPropertiesNames();
	
}

