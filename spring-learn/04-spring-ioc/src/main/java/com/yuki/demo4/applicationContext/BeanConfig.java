package com.yuki.demo4.applicationContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public Car car() {
        return new Car("BYD","yellow",15);
    }
}
