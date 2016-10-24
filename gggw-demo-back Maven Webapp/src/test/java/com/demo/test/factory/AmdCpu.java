package com.demo.test.factory;
/**
 * ClassName:AmdCpu <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-10-24 下午2:13:23 <br/>
 * @author   cgw 
 */
public class AmdCpu implements Cpu {

	 /**
     * CPU的针脚数
     */
    private int pins = 0;
    public  AmdCpu(int pins){
        this.pins = pins;
    }
    
    @Override
    public void calculate() {
        // TODO Auto-generated method stub
        System.out.println("AMD CPU的针脚数：" + pins);
    }

}

