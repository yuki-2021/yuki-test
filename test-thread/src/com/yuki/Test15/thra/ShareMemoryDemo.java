package com.yuki.Test15.thra;

import java.util.ArrayList;
import java.util.List;


/**
 * 共享变量
 *
 * @Author sdc
 */
public class ShareMemoryDemo {
    //共享对象
    private static int shared = 0;
    private static void incrShared(){
        shared ++;
    }
    static class ChildThread extends Thread {
        List list;
        public ChildThread(List list) {
            this.list = list;
        }
        @Override
        public void run() {
            incrShared();
            list.add(Thread.currentThread().getName());
        }
    }
    public static void main(String[] args) throws InterruptedException {
        List list = new ArrayList();
        Thread t1 = new ChildThread(list);
        Thread t2 = new ChildThread(list);
        //执行
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        //输出
        System.out.println(shared);
        System.out.println(list);
    }
}
