package com.yuki.demo4.resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;

import java.io.*;

public class FileResourceExample {
    public static void main(String[] args) throws IOException {
        String filePath = "E:\\yuki-test\\spring-learn\\04-spring-ioc\\src\\main\\resources\\conf\\file1.txt";
        String classPath = "conf/file1.txt";
        // 写入内容
        PathResource pathResource = new PathResource(filePath);
        OutputStream ops = pathResource.getOutputStream();
        ops.write("hello-yuki".getBytes());
        ops.close();

        // 读取
        ClassPathResource classPathResource = new ClassPathResource(classPath);
        ops = pathResource.getOutputStream();
        ops.write("hello-yuki222".getBytes());
        ops.close();


    }
}
