package com.yuki;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
public class DeleteTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void test(){
        int i = userMapper.deleteById(1);
        System.out.println(i);
    }

    @Test
    public void test2(){
        //update
        User user = new User();
        user.setEmail("aaa@@sdc.com");
        QueryWrapper<User> wrapper = Wrappers.<User>query().eq("name", "利于");
        int update = userMapper.update(user, wrapper);
    }
}
