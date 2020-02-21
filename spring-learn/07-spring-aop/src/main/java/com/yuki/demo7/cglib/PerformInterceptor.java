package com.yuki.demo7.cglib;


import com.yuki.demo7.monitor.Monitor;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class PerformInterceptor implements MethodInterceptor {


    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Monitor.begin(method.getName());
        Object res = methodProxy.invokeSuper(o, args);
        Monitor.end();
        return res;
    }
}
