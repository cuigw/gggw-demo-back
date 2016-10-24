package com.demo.test.factory;
/**
 * ClassName:AbstractFactory <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-10-24 下午2:10:14 <br/>
 * @author   cgw 
 */
public interface AbstractFactory {
    /**
     * 创建CPU对象
     * @return CPU对象
     */
    public Cpu createCpu();
    /**
     * 创建主板对象
     * @return 主板对象
     */
    public Mainboard createMainboard();
}

