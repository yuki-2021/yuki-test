package com.yuki.demo7.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class PreServe implements MethodBeforeAdvice {
    public void before(Method method, Object[] args, Object o) throws Throwable {
        System.out.println(" ==> 很高兴为您服务");
    }
}
