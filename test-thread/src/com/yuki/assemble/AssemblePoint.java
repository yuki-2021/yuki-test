package com.yuki.assemble;

public class AssemblePoint {
    private int n;

    public AssemblePoint(int n) {
        this.n = n;
    }

    public synchronized void await() throws InterruptedException {
        if(n > 0) {
            n--;
            if(n == 0) {
                System.out.println("所有人已经到达");
                notifyAll();
            } else {
                while(n != 0) {
                    wait();
                }
            }
        }
    }
}
