package com.gggw.controller.system;


import com.gggw.entity.system.BaseResource;
import com.gggw.entity.system.BaseRole;
import com.gggw.entity.system.BaseRoleResource;
import com.gggw.result.SisapResult;
import com.gggw.service.system.RoleResourceService;
import com.gggw.service.system.SysResourceService;
import com.gggw.service.system.SysRoleService;
import com.gggw.util.PageData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class RoleResourceController extends BaseController{

	@Autowired
	private SysResourceService sysResourceService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private RoleResourceService roleResourceService;

	/**
	 * 进入角色编辑页面（新增/修改）
	 */
	@RequestMapping(value="toRoleEdit")
	public ModelAndView toRoleEdit(BaseRole baseRole, @RequestParam("operatType") String operatType)throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("operatType", operatType);
		modelAndView.setViewName("ui/backend/system/roleEdit");
		return modelAndView;
	}

	
	//==================================       ajaxFunction start          =====================================//
	/**
	 * 获取所有资源
	 */
	@ResponseBody
	@RequestMapping(value="ajaxGetAllResource")
	public Object ajaxGetAllResource()throws Exception{
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
	
	/**
	 *  ajax 新增/修改角色
	 */
	@ResponseBody
	@RequestMapping(value="ajaxRoleEdit")
	public Object ajaxRoleEdit(BaseRole baseRole, @RequestParam("operatType") String operatType)throws Exception{
		SisapResult sisapResult = new SisapResult("0", "");		
		PageData requestParam = this.getPageData();
		try {
			//if ("0".equals(operatType)) {
				sysRoleService.addRole(baseRole);
				baseRole = sysRoleService.findByCode(baseRole);
				String[] resources = requestParam.getString("resources").split(",");
				if (resources.length > 0) {
					for (String resourceId : resources) {
						BaseRoleResource BaseRoleResource = new BaseRoleResource();
						BaseRoleResource.setRoleId(baseRole.getRoleId());
						BaseRoleResource.setResourceId(Integer.parseInt(resourceId));
						roleResourceService.addRoleResource(BaseRoleResource);
					}			
				}
				sisapResult.setError_info("添加成功!");
			//}			
		} catch (Exception e) {
			logger.error("RoleController --> ajaxRoleEdit error!", e);
			sisapResult.setError_no("1");
			sisapResult.setError_info("数据异常");
		}
		return sisapResult;
	}
	//==================================       ajaxFunction end          =====================================//
}

