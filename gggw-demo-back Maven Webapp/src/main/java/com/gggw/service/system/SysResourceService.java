package com.gggw.service.system;

import com.gggw.dao.DaoSupport;
import com.gggw.entity.system.BaseResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cgw on 2016-11-6.
 */
@Service("sysResourceService")
public class SysResourceService {

    @Resource(name = "daoSupport")
    private DaoSupport dao;

    public List<BaseResource> getAllResource() throws Exception {
        List<BaseResource> allResourceList = (List<BaseResource>) dao.findForList("BaseResourceMapper.selectAll", null);
        for (BaseResource baseResource : allResourceList) {
            List<BaseResource> kids = getKidResources(baseResource.getResourceId());
            baseResource.setKidResources(kids);
        }
        return allResourceList;
    }

    public List<BaseResource> getKidResources(Integer parentId) throws Exception {
        List<BaseResource> allResourceList =  (List<BaseResource>) dao.findForList("BaseResourceMapper.getKidResources", parentId);
        return allResourceList;
    }
}
