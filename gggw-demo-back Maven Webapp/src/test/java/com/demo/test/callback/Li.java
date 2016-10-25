package com.demo.test.callback;
/**
 * ClassName:Li <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-10-25 下午5:07:38 <br/>
 * @author   cgw 
 */
public class Li {
	
	public void excute (Callback callback, String question) {
		
		System.out.println("提出来的问题是：" + question);
		
        for(int i=0; i<10000;i++){  
        	 
        }  
 
        String result = "答案是......2";  
        
        callback.solve(result);
	}
}

