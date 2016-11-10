package com.gggw.controller.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gggw.entity.system.BaseSysUser;
import com.gggw.service.system.SysUserService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource(name="sysUserService")
	private SysUserService sysUserService;

	/**
	 * 进入用户管理页
	 */
	@RequestMapping(value="toUser")
	public ModelAndView toUser()throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ui/backend/system/userManager");
		return modelAndView;
	}
	
	/**
	 * 进入用户编辑页面（新增/修改）
	 */
	@RequestMapping(value="toUserEdit")
	public ModelAndView toUserEdit()throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ui/backend/system/userEdit");
		return modelAndView;
	}

	/**
	 * ajax 新增/修改 用户
	 */
	@RequestMapping(value="ajaxUserEdit")
	public Object ajaxUserEdit(BaseSysUser baseSysUser) {  //, @RequestParam("operatType") String operatType
		try {
			sysUserService.addUser(baseSysUser);
		} catch (Exception e) {
			logger.error("ajaxUserEdit --> error");
		}
		return "";
	}
}

