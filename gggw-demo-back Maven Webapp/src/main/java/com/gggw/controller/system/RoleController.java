package com.gggw.controller.system;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gggw.controller.base.BaseController;

/**
 * ClassName:RoleController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-11-23 下午4:36:19 <br/>
 * @author   cgw 
 */
@Controller
public class RoleController extends BaseController{
	/**
	 * 进入用户编辑页面（新增/修改）
	 */
	@RequestMapping(value="toRoleEdit")
	public ModelAndView toRoleEdit()throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ui/backend/system/roleEdit");
		return modelAndView;
	}
}

