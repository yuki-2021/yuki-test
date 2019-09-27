package com.yuki.thread;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("runable");
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}
