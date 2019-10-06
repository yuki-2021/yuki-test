package com.yuki.test.ioc;

import com.yuki.test.ioc.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class TestIocApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TestIocApplication.class, args);
//        JdbcProperty bean = run.getBean(JdbcProperty.class);
//        System.out.println(bean.getPassword());
}

}
