package com.demo.test.factory;
/**
 * ClassName:IntelFactory <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-10-24 下午2:18:23 <br/>
 * @author   cgw 
 */
public class IntelFactory implements AbstractFactory {

    @Override
    public Cpu createCpu() {
        // TODO Auto-generated method stub
        return new IntelCpu(755);
    }

    @Override
    public Mainboard createMainboard() {
        // TODO Auto-generated method stub
        return new IntelMainboard(755);
    }

}

