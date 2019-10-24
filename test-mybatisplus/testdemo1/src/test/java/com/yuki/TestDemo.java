package com.yuki;

import com.yuki.dao.UserMapper;
import com.yuki.entry.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TestDemo {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1(){
        User user = userMapper.selectById(1087982257332887553L);
        System.out.println(user);
    }

    @Test
    public void test2(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1087982257332887553L, 1088248166370832385L));
        users.forEach(System.out::println);
    }

    //managerID不可用 存储的是数据库中的列
    @Test
    public void test3(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","李静");
        map.put("age",25);
        userMapper.selectByMap(map).forEach(System.out::println);
    }

}