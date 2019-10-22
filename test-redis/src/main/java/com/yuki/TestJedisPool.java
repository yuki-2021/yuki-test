package com.yuki;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestJedisPool {

    public static void main(String[] args) {

        // init config
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(GenericObjectPoolConfig.DEFAULT_MAX_TOTAL * 5);
        poolConfig.setMaxIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE * 5);
        poolConfig.setMinIdle(GenericObjectPoolConfig.DEFAULT_MIN_IDLE * 5);
        poolConfig.setJmxEnabled(true);
        poolConfig.setMaxWaitMillis(3000);

        JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String sdc = jedis.get("sdc");
            System.out.println(sdc);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }
}
