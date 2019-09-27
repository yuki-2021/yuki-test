package com.yuki.thread;

public class InterruptExample {

    private static class MyThread1 extends Thread{
        @Override
        public void run() {
            try {
               while (true){
                   System.out.println(interrupted());
               }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread1 myThread1 = new MyThread1();
        myThread1.start();
        Thread.sleep(1000);
        myThread1.interrupt();
        System.out.println("main run");
    }
}
