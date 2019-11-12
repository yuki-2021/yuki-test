package org.hscoder.springboot.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsoleController {

    //输出数据频道
    public static final String CHANNEL_CONSOLE = "/topic/console";


    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 控制台页面
     *
     * @return
     */
    @GetMapping("/console")
    public String console() {
        return "console";
    }

    /**
     * 接收WebSocket消息方法
     * @param message
     */
    @MessageMapping("/message")
    public void onMessage(String message) {
        template.convertAndSend(CHANNEL_CONSOLE, "我收到了你的消息：" + message);
    }
}
