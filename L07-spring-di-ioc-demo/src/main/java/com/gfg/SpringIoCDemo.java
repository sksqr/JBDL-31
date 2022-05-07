package com.gfg;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;


public class SpringIoCDemo {

    public static void main(String[] args) {

//         Spring IoC
        Resource resource = new ClassPathResource("projectbeans.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        Engine engine1 = (Engine) factory.getBean("engine1");
        System.out.println(engine1);

        Engine engine2 = (Engine) factory.getBean("engine1");
        System.out.println(engine2);

//        Car car1 = (Car) factory.getBean("car1");
//        System.out.println(car1);
//





        // its an implementation of BeanFactor
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("projectbeans.xml");
//        ApplicationContext context = new ClassPathXmlApplicationContext("projectbeans.xml");
//        Engine engine2 = (Engine) context.getBean("engine2");
//        System.out.println(engine2);
//        context.close();



        //Spring dependency injection
//        Car hexa = (Car) context.getBean("car1");
//        hexa.run();

        

    }
}
