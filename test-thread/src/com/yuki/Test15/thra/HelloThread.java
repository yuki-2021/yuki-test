package com.yuki.Test15.thra;

public class HelloThread extends Thread {
    @Override
    public void run() {
        System.out.println("thread name: "+ Thread.currentThread().getName());
        System.out.println("hello");
    }

    public static void main(String[] args) {
        Thread thread = new HelloThread();
        //执行线程
        thread.start();
    }
}