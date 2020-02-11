package com.yuki.demo5.anno;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarConfig {

    @Bean(name = "car666")
    public Car car666() {
        Car car = new Car();
        car.setBrand("yuki");
        return car;
    }
}
