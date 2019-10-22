package com.yuki.test17;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 基于跳表 可以排序 没有使用锁 多线程可以同时写
 *
 * */
public class ConcurrentSkipListDemo {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentSkipListMap<>(
                Collections.reverseOrder());
        map.put("a", "abstract");
        map.put("c", "call");
        map.put("b", "basic");
        System.out.println(map.toString());
    }
}
