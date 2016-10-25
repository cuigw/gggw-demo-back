package com.demo.test.callback;

/**
 * ClassName:Wang <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date: 2016-10-25 下午5:02:26 <br/>
 * 
 * @author cgw
 */
public class Wang implements Callback {

	private Li li;

	public Wang(Li li) {
		this.li = li;
	}

	@Override
	public void solve(String result) {

		System.out.println("问题已经解决，结果是：" + result);

	}

	public void askQuestion(final String question) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				li.excute(Wang.this, question);

			}
		}).start();
		play();
	}

	public void play() {
		System.out.println("我要逛街去了");
	}

}
