package com.gggw.core.utils;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gggw.core.cache.PropertiesCache;


/**
 * ClassName:PropertiesUtil <br/>
 * Function: 配置文件读取工具  From redis . <br/>
 * Date:     2016-10-20 上午10:41:51 <br/>
 * @author   cgw 
 */
//@SuppressWarnings({"all"})
public class PropertiesUtil {
	
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	
	private static  Properties prop = new Properties();;
	
	public static String[] pwdNameList = {"db.password"};  
	
	public static synchronized void clear() {
		if (null != prop) {
			prop.clear();
		}	
	}
	
	public static synchronized void init() {
		Map<String, PropertiesCache> caches = SpringContext.getBeansOfType(PropertiesCache.class);
		if (null == caches || caches.isEmpty()) {
			throw new RuntimeException("系统初始化不正常，配置加载失败");
		}
		for (Map.Entry<String, PropertiesCache> entry : caches.entrySet()) {
			Map map = entry.getValue().getConfigData();
			if (null != map || !map.isEmpty()) {
				prop.putAll(map);
			}
		}
	}
	
	public static Properties getProp() {
		if (null == prop || prop.isEmpty()) {
			init();
		}
		return prop;
	}
	
	public static String get(String key) {
		return get(key, "");
	}
	
	public static String get(String key, String defaultValue) {
		String value = getProp().getProperty(key, defaultValue);

		if (null == value || value.toLowerCase().equals("null") || StringUtils.isBlank(value)
				|| (value.startsWith("${") && value.endsWith("}"))){
			value = defaultValue;
		}
		if(isPassword(key,value) && value.matches("^[A-F0-9]+$") && value.length()%16==0){
			return AESUtil.decrypt(value);
		}
		return value.trim();
	}
	
	public static int getInt(String key, int defaultValue) {
		String value = get(key, String.valueOf(defaultValue));
		return Integer.parseInt(value);
	}
	
	public static String getWithFormat(String key, String ... vals) {
		String msg = "";
		String format = getProp().getProperty(key);
		if (!StringUtils.isBlank(format)) {
			MessageFormat mf = new MessageFormat(format);
			msg = mf.format(vals);
		}
		return msg;
	}
	
	public static String getWithFormat(String key, Map<String, String> params) {
		String str = get(key, "");
		String regex = "\\$\\{([^)]*?)\\}";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		while (m.find()) {
			key = m.group(1);
			String value = params.get(key);
			str = str.replace("${" + key + "}", StringUtils.defaultIfEmpty(value, ""));
		}
		return str;
	}

	public static Integer get(String key, Integer def) {
		try {
			Integer res = def;
			String value = getProp().getProperty(key);
			if (null == value || StringUtils.isBlank(value)) {
				value = String.valueOf(def);
			}
			
			if(isPassword(key,value)&&value.matches("^[A-F0-9]+$") && value.length()%16==0){
				return Integer.parseInt(AESUtil.decrypt(value));
			}
			if (getProp().containsKey(key)){
				if(value == null || value.isEmpty() || value.toLowerCase().equals("null") 
						|| (value.startsWith("${") && value.endsWith("}"))){
					value = res.toString();
				}
				res = Integer.parseInt(value);
			}
			return res;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return def;
		}

	}

	public static Long get(String key, Long def) {
		try {
			Long res = def;
			String value = getProp().getProperty(key);
			if (null == value || StringUtils.isBlank(value)) {
				value = String.valueOf(def);
			}
			if(isPassword(key,value)&&value.matches("^[A-F0-9]+$") && value.length()%16==0){
				return Long.parseLong(AESUtil.decrypt(value));
			}
			if (getProp().containsKey(key)) res = Long.parseLong(value);
			return res;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return def;
		}

	}
	
	public static boolean isPassword(String key , String value){
		boolean flag = false;
		boolean flag2 = false;
		if(key!=null && value!=null && !"null".equals(value)){
			key = key.replace(".","_");
			for(String str : pwdNameList){
				if(str.equals(key.trim())){
					flag = true;
				}
			}
			if(key.trim().contains("password")&&!"db_password".equals(key.trim())){
				flag2 = true;
			}
		}
		return flag || flag2;
	}
 }

