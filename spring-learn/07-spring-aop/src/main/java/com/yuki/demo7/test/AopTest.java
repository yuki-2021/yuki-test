package com.yuki.demo7.test;

import com.yuki.demo7.advice.Greetable;
import com.yuki.demo7.service.LocalWaiter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("aop.xml");
        LocalWaiter waiter = (LocalWaiter) context.getBean("waiter");
        Greetable g = (Greetable) waiter;
        g.setGreetAble(false);
        waiter.greetTo("yuki");
    }
}
