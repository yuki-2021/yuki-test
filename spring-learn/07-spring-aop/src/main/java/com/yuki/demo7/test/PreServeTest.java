package com.yuki.demo7.test;

import com.yuki.demo7.advice.PreServe;
import com.yuki.demo7.service.LocalWaiter;
import com.yuki.demo7.service.Waiter;
import org.springframework.aop.framework.ProxyFactory;

public class PreServeTest {
    public static void main(String[] args) {
        LocalWaiter target = new LocalWaiter();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(new PreServe());
        Waiter waiter = (Waiter) proxyFactory.getProxy();
        waiter.greetTo("yuki");
    }
}
