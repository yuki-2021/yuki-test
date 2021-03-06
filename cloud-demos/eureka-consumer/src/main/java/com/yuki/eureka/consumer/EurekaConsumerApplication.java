package com.yuki.eureka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//把当前应用加入到服务治理体系中
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerApplication.class,args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
