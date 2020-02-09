package com.yuki.demo4.applicationContext;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlcontextTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(BeanConfig.class);
        Car car = context.getBean(Car.class);
        System.out.println(car);
    }

    private static void xmlTest() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:context/applicationContext.xml");

        Car car = context.getBean(Car.class);
        System.out.println(car);
    }
}
