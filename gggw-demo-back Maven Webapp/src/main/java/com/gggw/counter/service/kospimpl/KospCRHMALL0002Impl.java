package com.gggw.counter.service.kospimpl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.gggw.counter.service.AbstractKospService;
import com.gggw.counter.service.feature.CounterService0002;
import com.gggw.result.SisapResult;

/**
 * ClassName:KospCRHMALL0002Impl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-9-27 下午3:47:33 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
public class KospCRHMALL0002Impl extends AbstractKospService implements
	CounterService0002 {

	@Override
	public SisapResult excute(String userName, Map<String, Object> params) {

		System.out.println("=======================KospCRHMALL0002Impl :  excute     " + userName);
		return null;
	}

}

