package com.gggw.service.system;

import java.util.List;
import java.util.Map;

import com.gggw.dao.DaoSupport;
import com.gggw.entity.system.BaseRole;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by cgw on 2016-11-23.
 */
@Service("sysRoleService")
public class SysRoleService {
    @Resource(name = "daoSupport")
    private DaoSupport dao;
    
    /**
	 * 新增角色
	 */
	public int addRole(BaseRole baseRole) throws Exception{
		return (Integer)dao.save("BaseRoleMapper.insert", baseRole);
	}
	
	public BaseRole findByCode(BaseRole baseRole) throws Exception{
		return (BaseRole)dao.findForObject("BaseRoleMapper.findByCode", baseRole);
	}
	
	/**
	 * 分页查询
	 */
	public List<BaseRole> selectByRolePage(Map<String, String> params) throws Exception{
		return (List<BaseRole>) dao.findForList("BaseRoleMapper.selectByRolePage", params);
	}

	/**
	 * 分页查询总数
	 */
	public Integer selectByRolePageCount(Map<String, String> params) throws Exception{
		return (Integer)dao.findForObject("BaseRoleMapper.selectByRolePageCount", params);
	}
}
