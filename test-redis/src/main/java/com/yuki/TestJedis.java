package com.yuki;

import redis.clients.jedis.Jedis;

public class TestJedis {

    public static final String HOST = "127.0.0.1";
    public static final int PORT = 6379;

    public static void main(String[] args) {

        // init jedis
        Jedis jedis = new Jedis(HOST, PORT);

        // 操作
        String set = jedis.set("sdc", "sdc");
        String sdc = jedis.get("sdc");

        // 输出
        System.out.println(sdc);
    }
}
