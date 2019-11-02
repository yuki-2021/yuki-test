package com.yuki.test16.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    //可重入锁
    private Lock lock = new ReentrantLock();

    private volatile double money;

    //con
    public Account(double initialMoney) {
        this.money = initialMoney;
    }

    //add
    public void add(double money) {
        lock.lock();
        try {
            this.money += money;
        } finally {
            lock.unlock();
        }
    }

    //reduce
    public void reduce(double money) {
        lock.lock();
        try {
            this.money -= money;
        } finally {
            lock.unlock();
        }
    }


    public double getMoney() {
        return money;
    }
    void lock() {
        lock.lock();
    }
    void unlock() {
        lock.unlock();
    }
    boolean tryLock() {
        return lock.tryLock();
    }
}
