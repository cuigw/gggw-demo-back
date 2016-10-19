package com.gggw.core.cache;

import java.io.Serializable;
import java.util.Map;

import org.springframework.core.Ordered;

/**
 * ClassName:ICache <br/>
 * Function: 统一缓存. <br/>
 * Date:     2016-10-19 下午3:41:08 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface ICache<T> extends Serializable, Ordered {

	public void refresh() throws Exception;

	public String getId();

	public Map<String, T> getConfigData();
}

