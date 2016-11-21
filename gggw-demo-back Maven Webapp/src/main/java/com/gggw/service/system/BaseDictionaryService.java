package com.gggw.service.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gggw.dao.DaoSupport;
import com.gggw.entity.system.BaseDictionary;

/**
 * ClassName:BaseDictionaryService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-11-21 上午10:17:37 <br/>
 * @author   cgw 
 */
@Service
public class BaseDictionaryService {
	
	@Resource(name = "daoSupport")
    private DaoSupport dao;
	
	public List<BaseDictionary> getAll() throws Exception{
		return (List<BaseDictionary>)dao.findForList("BaseDictionaryMapper.selectAll", null);
	}
}

