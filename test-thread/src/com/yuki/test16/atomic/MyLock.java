package com.yuki.test16.atomic;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * 基于CAS实现悲观锁
 *
 * @Author sdc
 */
public class MyLock {
    //
    private AtomicInteger status = new AtomicInteger(0);

    //加锁 yield用于运行态转换成为Runable状态
    public void lock() {
        while(!status.compareAndSet(0, 1)) {
            Thread.yield();
        }
    }

    public void unlock() {
        status.compareAndSet(1, 0);
    }


}
