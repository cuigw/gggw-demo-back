package com.demo.test.cache;

import com.gggw.core.cache.PropertiesCache;

/**
 * ClassName:TestPropertiesCache <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-10-19 下午4:32:16 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestPropertiesCache extends PropertiesCache{
	
	@Override
	public String getId() {
		return "db";
	}

	@Override
	public String[] getPropertiesNames() {
		return new String[] {"/db/db.properties"};
	}

	public static void main(String[] args) {
		TestPropertiesCache tPropertiesCache = new TestPropertiesCache();
		try {
			tPropertiesCache.refresh();
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
}

