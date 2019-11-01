package com.yuki.param.validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.yuki.param.validator")
public class ParamValidatorApplication {

    public static void main(String[] args) {

        SpringApplication.run(ParamValidatorApplication.class, args);
    }

}
