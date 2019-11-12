package org.hscoder.springboot.redis;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis 配置
 * 
 * <pre>
 * EnableCaching注解启用了方法级缓存
 * </pre>
 * 
 * @see CachingConfigurerSupport
 * @author atp
 *
 */
@EnableCaching
@Configuration
public class RedisConfig {

    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    /**
     * 缓存管理，支持方法级注解
     * 
     * @param template
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager(RedisTemplate<String, Object> template) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(template);
        // 默认过期时间
        redisCacheManager.setDefaultExpiration(30 * 60 * 1000);
        return redisCacheManager;
    }

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
    @Bean
    public Jackson2JsonRedisSerializer<Object> jackson2JsonSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
                Object.class);

        // 初始化objectmapper
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
    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory connectionFactory,
            Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer) {

        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(connectionFactory);

        // 设置key/hashkey序列化
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);

        // 设置值序列化
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }

    /**
     * 定制方法缓存的key生成策略
     *
     * @return
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... args) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());

                for (Object arg : args) {
                    sb.append(arg.toString());
                }
                return sb.toString();
            }
        };
    }
}