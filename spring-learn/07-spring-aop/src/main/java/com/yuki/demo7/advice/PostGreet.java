package com.yuki.demo7.advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class PostGreet implements AfterReturningAdvice {
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println(" ===> bye bye~~~");
    }
}
