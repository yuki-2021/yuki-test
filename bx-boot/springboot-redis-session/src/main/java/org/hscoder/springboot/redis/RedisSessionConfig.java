package org.hscoder.springboot.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Redis 配置
 * @author atp
 *
 */
@EnableCaching
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 24
        * 3600, redisNamespace = "app", redisFlushMode = RedisFlushMode.ON_SAVE)
public class RedisSessionConfig {


    private static final Logger logger = LoggerFactory.getLogger(RedisSessionConfig.class);

    /**
     * 连接池配置
     *
     * @return
     */
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        JedisPoolConfig config = new JedisPoolConfig();
//
//        // 最大连接
//        config.setMaxTotal(10);
//        // 最大空闲，与最大连接保持一致，可减少频繁键链的开销
//        config.setMaxIdle(10);
//        // 连接最大空闲时间
//        config.setMinEvictableIdleTimeMillis(10 * 60 * 1000);
//        // 获取连接等待的最大时长
//        config.setMaxWaitMillis(30000);
//
//        // 进行空闲连接检测的时间间隔
//        config.setTimeBetweenEvictionRunsMillis(30 * 1000);
//        // 取消不必要的test，有利于性能提升
//        config.setTestOnBorrow(false);
//        config.setTestOnReturn(false);
//
//        JedisConnectionFactory factory = new JedisConnectionFactory(config);
//        factory.setHostName("127.0.0.1");
//        factory.setPort(6379);
//
//        logger.info("redis config init first");
//        return factory;
//    }

    /**
     * 序列化定制
     * 
     * @return
     */
    @Bean("springSessionDefaultRedisSerializer")
    public Jackson2JsonRedisSerializer<Object> jackson2JsonSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
                Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(mapper);
        return jackson2JsonRedisSerializer;
    }

    /**
     * 操作模板
     * 
     * @param connectionFactory
     * @param jackson2JsonRedisSerializer
     * @return
     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory connectionFactory,
//            Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer) {
//
//        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
//        template.setConnectionFactory(connectionFactory);
//
//        // 设置key/hashkey序列化
//        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
//        template.setKeySerializer(stringSerializer);
//        template.setHashKeySerializer(stringSerializer);
//
//        // 设置值序列化
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        template.setHashValueSerializer(jackson2JsonRedisSerializer);
//        template.afterPropertiesSet();
//
//        return template;
//    }

}