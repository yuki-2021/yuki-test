package com.yuki.test16.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    //可重入锁
    //默认不保证公平性
    private final Lock lock = new ReentrantLock();
    private volatile int count;

    //incr
    public void incr() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    //getCount
    public int getCount() {
        return count;
    }
}
