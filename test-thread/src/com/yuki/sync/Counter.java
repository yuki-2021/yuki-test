package com.yuki.sync;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private int count;
    public synchronized void incr(){
        count ++;
    }
    public synchronized int getCount() {
        return count;
    }
   // AtomicInteger
}