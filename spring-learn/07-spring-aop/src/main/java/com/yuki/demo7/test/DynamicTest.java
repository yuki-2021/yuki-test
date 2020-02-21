package com.yuki.demo7.test;

import com.yuki.demo7.dynamic.PerformInvocationHandler;
import com.yuki.demo7.service.LocalWaiter;
import com.yuki.demo7.service.Waiter;

import java.lang.reflect.Proxy;

public class DynamicTest {

    public static void main(String[] args) {
        LocalWaiter traget = new LocalWaiter();
        PerformInvocationHandler h = new PerformInvocationHandler(traget);
        // 创建代理对象
        Waiter waiter = (Waiter) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Waiter.class},
                h);
        // 代用往返
        waiter.serveTo();
    }
}
