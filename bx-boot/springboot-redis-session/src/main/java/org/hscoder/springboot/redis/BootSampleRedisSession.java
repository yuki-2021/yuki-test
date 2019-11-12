package org.hscoder.springboot.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BootSampleRedisSession {

    public static void main(String[] args) {
        SpringApplication.run(BootSampleRedisSession.class);  
    }

}
