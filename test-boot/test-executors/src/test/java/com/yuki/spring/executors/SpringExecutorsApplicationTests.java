package com.yuki.spring.executors;

import com.yuki.spring.executors.service.AsyncService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringExecutorsApplicationTests {


    @Autowired
    private AsyncService asyncService;


    @Test
    public void testAsync() {
        //打印线程名称
        System.out.println("AsyncServiceImpl的线程名称是：" + Thread.currentThread().getName());
        //调用方法
        asyncService.asyncHello();
    }

}
