package com.yuki.testdb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@SpringBootApplication
//定义JPA接口扫描路径
@EnableJpaRepositories(basePackages = "com.yuki.testdb")
//实例类扫描路径
@EntityScan(basePackages = "com.yuki.testdb")
//mapperScan
@MapperScan(basePackages = "com.yuki.testdb.dao",annotationClass = Repository.class)
public class TestDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestDbApplication.class, args);
    }

}
