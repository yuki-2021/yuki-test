package com.yuki.wait;

public class WaitThread extends Thread {

    //防止出现 变量不可见现象(加锁之后会重新读取 可以不加)
    private boolean fire = false;

    @Override
    public void run() {
        try {
            synchronized (this) {
                while(!fire) {
                    //调用wait方法需要持有对象锁
                    wait();
                }
            }
            System.out.println("fired");
        } catch(InterruptedException e) {
        }
    }


    public synchronized void fire() {
        this.fire = true;
        notify();
    }

    public static void main(String[] args) throws InterruptedException {
        WaitThread waitThread = new WaitThread();
        waitThread.start();
        Thread.sleep(1000);
        System.out.println("fire");
        waitThread.fire();
    }
}
