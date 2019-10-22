package com.yuki.test17;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapIteratorDemo {

    //test
    public static  void test() {
        //map put
        final ConcurrentHashMap<String, String> map =
                new ConcurrentHashMap<>();
        map.put("a", "abstract");
        map.put("b", "basic");


        Thread t1 = new Thread() {
            @Override
            public void run() {
                for(Map.Entry<String, String> entry : map.entrySet()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(entry.getKey() + "," + entry.getValue());
                }
            }
        };
        t1.start();

        try {
            Thread.sleep(100);
        } catch(InterruptedException e) {
        }

        //map.put("c", "call");
        map.put("g", "call");
    }
    public static void main(String[] args) {
        test();
    }
}
