package com.gggw.core.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gggw.entity.system.BaseDictionary;
import com.gggw.service.system.BaseDictionaryService;

/**
 * ClassName:BasedictionaryCache <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-11-21 上午9:48:08 <br/>
 * @author   cgw 
 */
@Component("baseDictionaryCache")
public class BaseDictionaryCache implements ICache<List<BaseDictionary>> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Map<String, List<BaseDictionary>> configData = new LinkedHashMap<String, List<BaseDictionary>>();
	
	@Autowired
	private BaseDictionaryService baseDictionaryService;

	@Override
	public int getOrder() {
		return 110;
	}
	
	@Override
	public String getId() {
		return "basedictionaryCache";
	}
	
	@Override
	public Map<String, List<BaseDictionary>> getConfigData() {
		return configData;
	}

	@Override
	public void refresh() throws Exception {
		List<BaseDictionary> list = baseDictionaryService.getAll();
		if (list != null && list.size() > 0) {
			configData.clear();
			for (BaseDictionary dict : list) {
				String dictEntry = dict.getDictEntry();
				List<BaseDictionary> subList = (List<BaseDictionary>) configData.get(dictEntry);
				if (subList == null) {
					subList = new ArrayList<BaseDictionary>();
					configData.put(dictEntry, subList);
				}
				subList.add(dict);
			}
		}
	}	

	/**
	 * 获取指定字典子项列表，按order_no进行排序
	 * @param dict_entry
	 * @return
	 */
	public List<BaseDictionary> getDictionaryList(String dict_entry) {
		List<BaseDictionary> subList = (List<BaseDictionary>)configData.get(dict_entry);
		if(subList!=null && subList.size()>0){
			Collections.sort(subList, new Comparator<BaseDictionary>() {

				@Override
				public int compare(BaseDictionary o1, BaseDictionary o2) {
					//调整排序
					return o1.getOrderNum().compareTo(o2.getOrderNum());
				}

			});
		}
		return subList;
	}
	
	/**
	 * 根据数据字典项和子项查询，返回Dictionary对象
	 * @param dict_entry
	 * @param subentry
	 * @return
	 */
	public BaseDictionary getDictionary(String dict_entry, String subentry) {
		List<BaseDictionary> DictList = getDictionaryList(dict_entry);
		if (DictList != null && DictList.size() > 0) {
			for (BaseDictionary Basedictionary : DictList) {
				if (StringUtils.equals(subentry, Basedictionary.getSubEntry())) {
					return Basedictionary;
				}
			}
		}
		return null;
	}
	
	/**
	 * 根据数据字典项和子项查询，返回字典子项中文，不存在则返回子项值
	 * @param dict_entry
	 * @param subentry
	 * @return
	 */
	public String getDictCaption(String dict_entry, String subentry) {
		BaseDictionary dict = getDictionary(dict_entry, subentry);
		if (dict != null) {
			return dict.getDictPrompt();
		}
		return subentry;
	}
}

