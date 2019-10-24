package com.yuki.Test15.sync;

public class Counter {
    private int count;
    public synchronized void incr(){
        count ++;
    }
    public synchronized int getCount() {
        return count;
    }
}