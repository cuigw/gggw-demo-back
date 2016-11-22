package com.gggw.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gggw.core.cache.BaseDictionaryCache;

/**
 * ClassName:DictionaryController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-11-22 上午9:14:54 <br/>
 * @author   cgw 
 */
@Controller
public class DictionaryController {
	
	@Autowired
	private BaseDictionaryCache baseDictionaryCache;

	@RequestMapping(value="getAllDictionary")
	@ResponseBody
	public Object getAllDictionary() {		
		return baseDictionaryCache.getConfigData();
	}
}

