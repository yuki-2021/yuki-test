package com.yuki.test.ioc.config;

import com.yuki.test.ioc.Animal;
import org.springframework.context.annotation.*;

//注解配置
@Configuration
//开启组件扫描
@ComponentScan(basePackages = "com.yuki.test.ioc")
public class AppConfig {

    //配置Bean类
    @Bean
    @Primary
    @Profile("dev")
    public Animal animal(){
        Animal animal = new Animal();
        animal.setAge(10);
        animal.setName("dog");
        return animal;
    }
}
