package com.yuki.demo7.advisor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("aop2.xml");
        Waiter waiter = context.getBean("waiter3", Waiter.class);
        Seller seller = context.getBean("seller3", Seller.class);
        waiter.greetTo("yuki");
        seller.greetTo("yuki");
        WaiterDelegate waiterDelegate = new WaiterDelegate(waiter);
        waiterDelegate.serve("yuki");
    }
}
