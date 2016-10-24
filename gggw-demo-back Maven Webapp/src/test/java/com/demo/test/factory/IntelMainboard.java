package com.demo.test.factory;


/**
 * ClassName:IntelMainboard <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-10-24 下午2:14:05 <br/>
 * @author   cgw 
 */
public class IntelMainboard implements Mainboard {

	/**
	 * CPU插槽的孔数
	 */
	private int cpuHoles = 0;

	/**
	 * 构造方法，传入CPU插槽的孔数
	 * 
	 * @param cpuHoles
	 */
	public IntelMainboard(int cpuHoles) {
		this.cpuHoles = cpuHoles;
	}

	@Override
	public void installCPU() {
		// TODO Auto-generated method stub
		System.out.println("Intel主板的CPU插槽孔数是：" + cpuHoles);
	}

}

