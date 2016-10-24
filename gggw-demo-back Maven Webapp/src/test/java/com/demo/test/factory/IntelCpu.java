package com.demo.test.factory;
/**
 * ClassName:IntelCpu <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-10-24 下午2:12:47 <br/>
 * @author   cgw 
 */
public class IntelCpu implements Cpu {

	/**
     * CPU的针脚数
     */
    private int pins = 0;
    public  IntelCpu(int pins){
        this.pins = pins;
    }
    
    @Override
    public void calculate() {
        // TODO Auto-generated method stub
        System.out.println("Intel CPU的针脚数：" + pins);
    }

}

