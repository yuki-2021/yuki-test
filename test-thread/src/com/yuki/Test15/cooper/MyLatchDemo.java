package com.yuki.Test15.cooper;


public class MyLatchDemo {

    int count;

    public MyLatchDemo(int count) {
        this.count = count;
    }

    public synchronized void await() throws InterruptedException {
        while (count > 0) {
            wait();
        }
    }

    public synchronized void countDown() {
        count--;
        if (count <= 0) {
            notifyAll();
        }
    }

    //worker结束后调用cutdown
    static class Worker extends Thread {
        MyLatchDemo latch;

        public Worker(MyLatchDemo latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                //simulate working on com.yuki.time.task.task
                Thread.sleep((int) (Math.random() * 1000));
                this.latch.countDown();
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int workerNum = 100;
        MyLatchDemo latch = new MyLatchDemo(workerNum);
        Worker[] workers = new Worker[workerNum];
        for (int i = 0; i < workerNum; i++) {
            workers[i] = new Worker(latch);
            workers[i].start();
        }
        latch.await();
        System.out.println("collect worker results");
    }
}
