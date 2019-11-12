package org.hscoder.springboot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@SpringBootApplication
public class WebBoot {

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(WebBoot.class);

        // 指定PID生成，默认输出到application.pid
        app.addListeners(new ApplicationPidFileWriter());
        app.run(args);
    }

    //注册后置处理器 用于参数验证
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
