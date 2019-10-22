package com.yuki.Test15.thra;


/**
 * 竞态条件
 *
 * @Author sdc
 */
public class CounterThread extends Thread {
    private static int counter = 0;
    @Override
    public void run() {
        for(int i = 0; i < 1000; i++) {
            counter++;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        int num = 1000;
        //创建1000个线程
        Thread[] threads = new Thread[num];
        for(int i = 0; i < num; i++) {
            threads[i] = new CounterThread();
            threads[i].start();
        }
        for(int i = 0; i < num; i++) {
            threads[i].join();
        }
        System.out.println(counter);
    }
}
