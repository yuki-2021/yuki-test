package com.yuki.wait;

class Producer extends Thread {
    MyBlockingQueue<String> queue;
    public Producer(MyBlockingQueue<String> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        int num = 0;
        try {
            while(true) {
                String task = String.valueOf(num);
                queue.put(task);
                System.out.println("produce task " + task);
                num++;
                Thread.sleep((int) (Math.random() * 100));
            }
        } catch (InterruptedException e) {
        }
    }
}

class Consumer extends Thread {
    MyBlockingQueue<String> queue;
    public Consumer(MyBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while(true) {
                String task = queue.take();
                System.out.println("handle task " + task);
                Thread.sleep((int)(Math.random()*100));
            }
        } catch(InterruptedException e) {
        }
    }


    public static void main(String[] args) {
        MyBlockingQueue<String> queue = new MyBlockingQueue<>(10);
        new Producer(queue).start();
        new Consumer(queue).start();
    }
}
