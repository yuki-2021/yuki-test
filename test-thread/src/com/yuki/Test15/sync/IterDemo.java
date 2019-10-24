package com.yuki.Test15.sync;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 便利容器 另外线程修改容器
 * 解决办法 在便利的时候加锁
 *
 * @Author sdc
 */
public class IterDemo {
    private static void startModifyThread(final List<String> list) {
        Thread modifyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
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
                    for (String str : list) {
                    }
                }
            }
        });
        iteratorThread.start();
    }

    public static void main(String[] args) {
        //新建数组
        final List<String> list = Collections
                .synchronizedList(new ArrayList<String>());
        startIteratorThread(list);
        startModifyThread(list);
    }
}
