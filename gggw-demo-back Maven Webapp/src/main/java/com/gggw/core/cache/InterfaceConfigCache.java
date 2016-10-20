package com.gggw.core.cache;

import org.springframework.stereotype.Component;

/**
 * ClassName:DBConfigCache <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-10-19 下午6:19:36 <br/>
 * @author   cgw 
 */
@Component
public class InterfaceConfigCache extends PropertiesCache {

	@Override
	public String getId() {
		return "interface";
	}

	@Override
	public String[] getPropertiesNames() {
		return new String[] {"/interface/interface_config.properties"};
	}
}

