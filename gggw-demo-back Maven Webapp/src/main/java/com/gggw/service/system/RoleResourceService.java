package com.gggw.service.system;

import com.gggw.dao.DaoSupport;
import com.gggw.entity.system.BaseRoleResource;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cgw on 2016-11-6.
 */
@Service("roleResourceService")
public class RoleResourceService {

    @Resource(name = "daoSupport")
    private DaoSupport dao;

    /**
	 * 新增
	 */
	public int addRoleResource(BaseRoleResource baseRoleResource) throws Exception{
		return (Integer)dao.save("BaseRoleResourceMapper.insert", baseRoleResource);
	}
}
