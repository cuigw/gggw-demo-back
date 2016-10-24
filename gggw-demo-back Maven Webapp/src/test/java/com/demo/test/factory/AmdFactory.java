package com.demo.test.factory;
/**
 * ClassName:AmdFactory <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-10-24 下午2:19:11 <br/>
 * @author   cgw 
 */
public class AmdFactory implements AbstractFactory {

	@Override
	public Cpu createCpu() {
		// TODO Auto-generated method stub
		return new IntelCpu(938);
	}

	@Override
	public Mainboard createMainboard() {
		// TODO Auto-generated method stub
		return new IntelMainboard(938);
	}

}

