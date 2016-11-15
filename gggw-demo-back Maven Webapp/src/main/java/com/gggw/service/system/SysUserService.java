/**
 * Project Name:gggw-demo-backend Maven Webapp
 * File Name:SysUserService.java
 * Package Name:com.gggw.service.system
 * Date:2016-6-24下午2:39:04
 * Copyright (c) 2016, 502269006@qq.com All Rights Reserved.
 *
*/

package com.gggw.service.system;

import javax.annotation.Resource;

import com.gggw.entity.system.BaseSysUser;
import org.springframework.stereotype.Service;

import com.gggw.dao.DaoSupport;

import java.util.List;
import java.util.Map;

/**
 * ClassName:SysUserService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016-6-24 下午2:39:04 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service("sysUserService")
public class SysUserService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**
	 * findByUserNo:(通过登录的用户名查询用户). <br/>
	 */
	public BaseSysUser findByUserNo(BaseSysUser user)throws Exception{
		return (BaseSysUser)dao.findForObject("BaseSysUserMapper.findByUserNo", user);
	}
	
	/**
	 * 查询所有用户
	 */
	public List<BaseSysUser> getUserListAll(Map<String, Integer> params) throws Exception{
		return (List<BaseSysUser>) dao.findForList("BaseSysUserMapper.selectAll", params);
	}

	/**
	 * 查询所有用户的总数
	 */
	public Integer getUserListAllCount() throws  Exception{
		return (Integer)dao.findForObject("BaseSysUserMapper.selectAllCount", null);
	}

	/**
	 * 新增用户
	 */
	public void addUser(BaseSysUser baseSysUser) throws Exception{
		dao.findForObject("BaseSysUserMapper.insert", baseSysUser);
	}
}

