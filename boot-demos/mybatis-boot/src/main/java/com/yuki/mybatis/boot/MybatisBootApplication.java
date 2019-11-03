package com.yuki.mybatis.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class MybatisBootApplication {

    public static void main(String[] args) {

        SpringApplication.run(MybatisBootApplication.class,args);

    }
}
