package com.yuki.test17;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * HashMap执行rehash操作 死锁 内存溢出
 *
 * */
public class HashMapDemo {

    public static void unsafeConcurrentUpdate() {
        // new hashmap
        final Map<Integer, Integer> map = new HashMap<>();

        //foreach
        for(int i = 0; i < 1000; i++) {
            Thread t = new Thread() {
                Random rnd = new Random();
                @Override
                public void run() {
                    for(int i = 0; i < 1000; i++) {
                        map.put(rnd.nextInt(), 1);
                    }
                }
            };
            t.start();
        }
    }


    public static void main(String[] args) {
        unsafeConcurrentUpdate();
    }
}
