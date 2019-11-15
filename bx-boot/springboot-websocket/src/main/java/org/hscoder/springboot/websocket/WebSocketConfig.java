package org.hscoder.springboot.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

//启用WebSocket消息代理
//WebSocket消息代理配置
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

   //配置WebSocket消息代理
    //用于做消息路由配置，包括订阅主题、方法映射路径
   @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        //开启消息代理(客户端订阅)
        //设置订阅通道(客户端可订阅)
        config.enableSimpleBroker("/topic");

        //设置地址前缀
        //接收APP(客户端)消息的路由前缀，可通过@MessageMapping 映射到方法
        config.setApplicationDestinationPrefixes("/app");
    }


    //注册Stomp端点
    //用于添加端点，即浏览器通过 ws://xxx 能访问到的路径
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        //websocket 连接端点
        registry.addEndpoint("/backend").withSockJS();
    }

    //配置WebSocket传输
    public void configureWebSocketTransport(final WebSocketTransportRegistration registration) {

        //配置拦截器
        //添加装饰器 相当于拦截器
        registration.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
            @Override
            public WebSocketHandler decorate(final WebSocketHandler handler) {
                //返回WebSocket装饰器
                return new WebSocketHandlerDecorator(handler) {

                    //连接开启
                    @Override
                    public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
                        String username = session.getPrincipal() != null? session.getPrincipal().getName(): "GUEST";
                        logger.info("{} connect.", username);
                        super.afterConnectionEstablished(session);
                    }

                    //连接关闭
                    @Override
                    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
                        String username = session.getPrincipal() != null? session.getPrincipal().getName(): "GUEST";
                        logger.info("{} disconnect.", username);
                        super.afterConnectionClosed(session, closeStatus);
                    }
                };
            }
        });
        super.configureWebSocketTransport(registration);
    }
}

