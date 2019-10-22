package com.yuki.spring.executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.*;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringExecutorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringExecutorsApplication.class, args);
    }

}
