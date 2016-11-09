package com.gggw.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gggw.controller.base.BaseController;
import com.gggw.core.annotation.NoLogin;

/**
 * ClassName:UserController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-11-9 下午4:56:06 <br/>
 * @author   cgw 
 */
@Controller
public class UserController extends BaseController{

	/**
	 * 进入用户管理页
	 */
	@NoLogin
	@RequestMapping(value="toUser")
	public ModelAndView toUser(HttpServletRequest request, HttpServletResponse response)throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ui/backend/system/userManager");
		return modelAndView;
	}
	
	/**
	 * 进入用户编辑页面（新增/修改）
	 */
	@NoLogin
	@RequestMapping(value="toUserEdit")
	public ModelAndView toUserEdit(HttpServletRequest request, HttpServletResponse response)throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ui/backend/system/userEdit");
		return modelAndView;
	}
}

