package com.yuki;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class TestPipeline {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1");
        // 1)生成pipeline对象
        Pipeline pipeline = jedis.pipelined();
        // 2)pipeline执行命令，注意此时命令并未真正执行
        String[] keys =new String[]{};
        for (String key : keys) {
            pipeline.del(key);
        }
        // 3)执行命令
        pipeline.sync();
    }
}
