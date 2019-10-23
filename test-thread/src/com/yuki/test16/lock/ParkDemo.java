package com.yuki.test16.lock;

import java.util.concurrent.locks.LockSupport;

public class ParkDemo {
    public static void main(String[] args) throws InterruptedException {

        //new thread
        Thread t = new Thread (){
            public void run(){
                //set thread watting
                LockSupport.park();
                System.out.println("exit");
            }
        };
        t.start();

        //unpark
        Thread.sleep(1000);
        LockSupport.unpark(t);
    }
}
