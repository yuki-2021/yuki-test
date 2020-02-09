package com.yuki.demo4.resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class EncodedResourceExample {

    public static void main(String[] args) throws IOException {
        // 1. 资源编码
        ClassPathResource classPathResource = new ClassPathResource("conf/file1.txt");
        EncodedResource res = new EncodedResource(classPathResource, "UTF-8");

        // 2. 读取资源
        String str = FileCopyUtils.copyToString(res.getReader());

        // 3. 输出资源
        System.out.println(str);
    }
}
