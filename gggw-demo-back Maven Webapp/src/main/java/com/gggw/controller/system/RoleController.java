package com.gggw.controller.system;


import com.gggw.entity.system.BaseResource;
import com.gggw.service.system.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gggw.controller.base.BaseController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:RoleController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-11-23 下午4:36:19 <br/>
 * @author   cgw 
 */
@Controller
public class RoleController extends BaseController{

	@Autowired
	private SysResourceService sysResourceService;

	/**
	 * 进入用户编辑页面（新增/修改）
	 */
	@RequestMapping(value="toRoleEdit")
	public ModelAndView toRoleEdit()throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ui/backend/system/roleEdit");
		return modelAndView;
	}

	/**
	 * 获取所有资源
	 */
	@ResponseBody
	@RequestMapping(value="getAllResource")
	public Object getAllResource()throws Exception{
		List<BaseResource> allResourceList = sysResourceService.getAllResource();
		List<Map<String, Object>>  resources = new ArrayList<Map<String, Object>>();
		for (BaseResource br : allResourceList) {
			Map<String, Object> resource = new HashMap<String, Object>();
			resource.put("id", br.getResourceId());
			resource.put("pId", br.getParentId());
			resource.put("name", br.getResourceName());
			resources.add(resource);
		}
		return resources;
	}
}

