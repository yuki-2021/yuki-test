package com.yuki.demo4.life;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("life/applicationContext.xml");


        Car car = context.getBean(Car.class);
        System.out.println(car);
    }
}
