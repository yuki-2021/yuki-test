package com.yuki.spring.executors.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@Configuration
public class AsyncConfig implements AsyncConfigurer {

    //获取线程池
    @Override
    public Executor getAsyncExecutor() {
        System.out.println("init customize executors !!!");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(30);
        //设置核心线程数
        executor.setCorePoolSize(10);
        //线程队列最大线程数
        executor.setQueueCapacity(2000);
        executor.initialize();
        return executor;
    }

    //线程异常处理
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
