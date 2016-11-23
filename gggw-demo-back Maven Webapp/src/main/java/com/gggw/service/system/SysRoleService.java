package com.gggw.service.system;

import com.gggw.dao.DaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by cgw on 2016-11-23.
 */
@Service("sysRoleService")
public class SysRoleService {
    @Resource(name = "daoSupport")
    private DaoSupport dao;
}
