package com.yuki.demo5.fac;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *  desc: 'testFac'
 *  author :yuki
 *
 * */
public class TestFac {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("fac/applicationContext.xml");
        Car car = context.getBean("car",Car.class);
        System.out.println(car);
        Car car2 = context.getBean("car2",Car.class);
        System.out.println(car2);
    }
}
