package com.yuki.test.aop.service.impl;

import com.yuki.test.aop.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    //连接点
    @Override
    public void eat() {
        System.out.println("正在吃饭");
    }
}
