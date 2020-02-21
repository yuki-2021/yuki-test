package com.yuki.demo7.test;

import com.yuki.demo7.cglib.PerformInterceptor;
import com.yuki.demo7.service.LocalWaiter;
import com.yuki.demo7.service.Waiter;
import org.springframework.cglib.proxy.Enhancer;

public class CglibTest {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(LocalWaiter.class);
        enhancer.setCallback(new PerformInterceptor());
        Waiter waiter = (Waiter) enhancer.create();
        waiter.serveTo();
    }
}
