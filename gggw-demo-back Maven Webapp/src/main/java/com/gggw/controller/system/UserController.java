package com.gggw.controller.system;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.gggw.entity.PageForm;
import com.gggw.entity.Paginator;
import com.gggw.entity.system.BaseSysUser;
import com.gggw.result.SisapResult;
import com.gggw.service.system.SysUserService;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gggw.controller.base.BaseController;
import com.gggw.core.utils.AESUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public ModelAndView toUserEdit(@RequestParam("operatType") String operatType)throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("operatType", operatType);
		modelAndView.setViewName("ui/backend/system/userEdit");
		return modelAndView;
	}

	
	//==================================       ajaxFunction start          =====================================//
	
    /**
	 * ajax 获取分页数据
	 */
	@RequestMapping(value="ajaxUserList")
	@ResponseBody
	public Object ajaxUserList(@Valid PageForm pageForm) {
		List<BaseSysUser> userList = new ArrayList<BaseSysUser>();
		Map<String, Integer> params = new HashMap<String, Integer>();
		int userListCount = 0;
		try {
			params.put("start", pageForm.getStart());
			params.put("end", pageForm.getStart() + pageForm.getLength());
			userList = sysUserService.getUserListAll(params);
			userListCount = sysUserService.getUserListAllCount();
		} catch (Exception e) {
			logger.error("UserController --> ajaxUserList error!", e);
		}
		return new Paginator(userListCount, userListCount, userList, pageForm.getDraw());
	}
	
	/**
	 * ajax 新增/修改 用户
	 */
	@RequestMapping(value="ajaxUserEdit")
	@ResponseBody
	public Object ajaxUserEdit(BaseSysUser baseSysUser, @RequestParam("operatType") String operatType) {
		SisapResult sisapResult = new SisapResult("0", "添加成功!");
		try {
			
			if ("0".equals(operatType)) {
				BaseSysUser userIsExist = sysUserService.findByUserNo(baseSysUser);
				if (null != userIsExist) {
					sisapResult.setError_no("1");
					sisapResult.setError_info("已存在");
					return sisapResult;
				}
				baseSysUser.setUserPwd(AESUtil.encrypt(baseSysUser.getUserPwd(), baseSysUser.getUserNo()+"nmb"));
				sysUserService.addUser(baseSysUser);
			}	
			
		} catch (Exception e) {
			logger.error("UserController --> ajaxUserEdit error!");
			sisapResult.setError_no("1");
			sisapResult.setError_info("异常");
		}
		return sisapResult;
	}
	
	//==================================       ajaxFunction end          =====================================//
}

