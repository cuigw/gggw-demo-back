package com.gggw.core.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.gggw.core.utils.SpringContext;

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
	
}

