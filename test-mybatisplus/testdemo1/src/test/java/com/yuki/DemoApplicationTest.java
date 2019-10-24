package com.yuki;

import com.yuki.dao.UserMapper;
import com.yuki.entry.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void select(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    //主键ID: 基于雪花算法
    //字段映射规则：驼峰 下划线
    @Test
    public void insert(){
        User user = new User();
        user.setName("李青");
        user.setAge(11);
        user.setManagerId(1087982257332887553L);
        user.setCreateTime(LocalDateTime.now());

        int row = userMapper.insert(user);
        System.out.println("影响记录数 "  + row);
    }

    @Test
    public void insert2(){
        User user = new User();
        user.setName("李晴");
        user.setAge(11);
        user.setManagerId(1087982257332887553L);
        user.setCreateTime(LocalDateTime.now());

        int row = userMapper.insert(user);
        System.out.println("影响记录数 "  + row);
    }
}