package com.yuki.demo7.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AroundGreet implements MethodInterceptor {
    public Object invoke(MethodInvocation proceed) throws Throwable {

        Object res = proceed.proceed();
        System.out.println("嘤嘤嘤(╥╯^╰╥)");
        return res;
    }
}
