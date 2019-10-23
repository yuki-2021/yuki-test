package com.yuki.see;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SeeDemo {

    //volatile保证读写最新值
    //不加violate会卡住
    private static volatile boolean shutdown = false;

    static class HelloThread extends Thread {
        @Override
        public void run() {
            while(!shutdown){
                // do nothing
            }
            System.out.println("exit hello");
        }
    }
//    public static void main(String[] args) throws InterruptedException {
//        new HelloThread().start();
//        Thread.sleep(1000);
//        shutdown = true;
//        System.out.println("exit main");
//    }

    private static void startModifyThread(final List<String> list) {
        Thread modifyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 100; i++) {
                    list.add("item " + i);
                    try {
                        Thread.sleep((int) (Math.random() * 10));
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        modifyThread.start();
    }
    private static void startIteratorThread(final List<String> list) {
        Thread iteratorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for(String str : list) {
                    }
                }
            }
        });
        iteratorThread.start();
    }
    public static void main(String[] args) {
//        final List<String> list = Collections
//                .synchronizedList(new ArrayList<String>());
//        startIteratorThread(list);
//        startModifyThread(list);

        String a = "aaa";
        String b = new String(a);

        byte[] bytes = a.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);
        }
        byte[] bytesa = a.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytesa[i]);
        }
    }
}
