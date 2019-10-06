package com.yuki.test.aop.plus.impl;

import com.yuki.test.aop.plus.UserValidator;

public class UserValidatorImpl implements UserValidator {
    //引入内容
    @Override
    public boolean isReady() {
        System.out.println("已经准备好");
        return true;
    }
}
