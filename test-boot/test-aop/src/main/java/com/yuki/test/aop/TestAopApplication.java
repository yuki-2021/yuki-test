package com.yuki.test.aop;

import com.yuki.test.aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestAopApplication {


    public static void main(String[] args) {
        SpringApplication.run(TestAopApplication.class, args);
    }

}
