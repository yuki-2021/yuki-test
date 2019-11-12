package org.hscoder.springboot.redis;

import java.util.Date;

import org.hscoder.springboot.simplebuild.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * redis 订阅推送案例
 * 
 * @author atp
 *
 */
@Service
public class RedisPubSub {  
    private static final Logger logger = LoggerFactory.getLogger(RedisPubSub.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private ChannelTopic topic = new ChannelTopic("/redis/pubsub");

    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    private void schedule() {
        logger.info("publish message");
        publish("admin", "hey you must go now!");
    }

    /**
     * 推送消息
     * 
     * @param publisher
     * @param message
     */
    public void publish(String publisher, String content) {
        logger.info("message send {} by {}", content, publisher);

        SimpleMessage pushMsg = new SimpleMessage();
        pushMsg.setContent(content);
        pushMsg.setCreateTime(new Date());
        pushMsg.setPublisher(publisher);

        redisTemplate.convertAndSend(topic.getTopic(), pushMsg);
    }

    @Component
    public static class MessageSubscriber {

        public void onMessage(SimpleMessage message, String pattern) {
            logger.info("topic {} received {} ", pattern, JsonUtil.toJson(message));
        }
    }

    public static class SimpleMessage {

        private String publisher;
        private String content;
        private Date createTime;

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

    }

    /**
     * 订阅配置
     * 
     * @author atp
     *
     */
    @Configuration
    public static class ReidsPubSubConfig {
        /**
         * 将订阅器绑定到容器
         * 
         * @param connectionFactory
         * @param listenerAdapter
         * @return
         */
        @Bean
        public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                MessageListenerAdapter listener) {

            RedisMessageListenerContainer container = new RedisMessageListenerContainer();
            container.setConnectionFactory(connectionFactory);
            container.addMessageListener(listener, new PatternTopic("/redis/*"));
            return container;
        }

        /**
         * 消息监听器，使用MessageAdapter可实现自动化解码及方法代理
         * 
         * @return
         */
        @Bean
        public MessageListenerAdapter listener(Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer,
                MessageSubscriber subscriber) {
            MessageListenerAdapter adapter = new MessageListenerAdapter(subscriber, "onMessage");
            adapter.setSerializer(jackson2JsonRedisSerializer);
            adapter.afterPropertiesSet();
            return adapter;
        }
    }

}
