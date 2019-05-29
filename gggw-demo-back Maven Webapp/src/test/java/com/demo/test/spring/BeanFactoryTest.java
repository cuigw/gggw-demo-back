package com.demo.test.spring;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class BeanFactoryTest {

    @Test
    public void testSimpleLoad() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("spring-test.xml"));
        MyTestBean bean = (MyTestBean)bf.getBean("myTestBean");
        System.out.println(bean.getTestStr());
    }
}
