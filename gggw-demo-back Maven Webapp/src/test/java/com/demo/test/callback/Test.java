package com.demo.test.callback;
/**
 * ClassName:Test <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-10-25 下午5:13:10 <br/>
 * @author   cgw 
 */
public class Test {
	public static void main(String[] args) {
		Wang w = new Wang(new Li());
		w.askQuestion(" 1 + 1 = ? ");
	}
}

