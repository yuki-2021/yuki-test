package com.yuki.demo5.inject;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InjectTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:inject/applicationContext.xml");

        Car car = context.getBean("car2",Car.class);
        System.out.println(car);
    }
}
