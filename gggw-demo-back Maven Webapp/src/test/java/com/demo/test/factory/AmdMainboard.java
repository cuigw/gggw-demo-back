package com.demo.test.factory;
/**
 * ClassName:AmdMainboard <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-10-24 下午2:17:04 <br/>
 * @author   cgw 
 */
public class AmdMainboard implements Mainboard {

	/**
	 * CPU插槽的孔数
	 */
	private int cpuHoles = 0;

	/**
	 * 构造方法，传入CPU插槽的孔数
	 * 
	 * @param cpuHoles
	 */
	public AmdMainboard(int cpuHoles) {
		this.cpuHoles = cpuHoles;
	}

	@Override
	public void installCPU() {
		// TODO Auto-generated method stub
		System.out.println("AMD主板的CPU插槽孔数是：" + cpuHoles);
	}
}

