package com.yuki.spring.executors.service.impl;

import com.yuki.spring.executors.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService {

    //多线程
    @Async
    @Override
    public void asyncHello() {
        //打印线程名称
        System.out.println("AsyncServiceImpl的线程名称是：" + Thread.currentThread().getName());
    }
}
