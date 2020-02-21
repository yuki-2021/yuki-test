package com.yuki.demo7.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class ExceptionGreet implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        System.out.println("。。。。");
    }
}
