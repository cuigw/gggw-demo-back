package com.demo.test.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * ClassName:map集合遍历的测试类 <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-10-17 下午1:31:58 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MapTraversalTest {
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "cgw");
		map.put("age", 18);
		map.put("sex", "男");
		
		/**
		 * 方法一： 直接使用entry + for-each 
		 *     优势：速度快,简洁明了
		 *     劣势：无法做删除操作,
		 *     		jdk 1.5+
		 */
		for(Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		
		/**
		 * 方法二：使用map.keySet() + map.values()
		 * 		优势：速度比方法一更快
		 * 		劣势： 只能获取key或value
		 */
		for (String key : map.keySet()) {
			System.out.println(key);
		}
		
		for (Object o : map.values()) {
			System.out.println(o);
		}
		
		/**
		 * 方法三： 使用iterator()
		 * 		优势：可以在操作过程中删除元素
		 * 		劣势：比方法一慢
		 */
		Iterator<Entry<String, Object>> itr = map.entrySet().iterator();
		Set<Map.Entry<String, Object>> entryMap = map.entrySet();
		Iterator<Entry<String, Object>> itr2 = entryMap.iterator();
        while (itr.hasNext()) {  
            Map.Entry<String, Object> entry = (Entry) itr.next();  
            if (entry.getKey().equals("name")) {
            	itr.remove();
            }
            System.out.println(entry.getKey() + "--->" + entry.getValue());  
        }  
        
        System.out.println(map);
	}
}

