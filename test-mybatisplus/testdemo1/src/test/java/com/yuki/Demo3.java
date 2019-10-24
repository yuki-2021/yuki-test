package com.yuki;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
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


/**
 * 查询构造器
 *
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class Demo3 {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void test1() {
        String name = "李";
        String email = "";
        //列名
        QueryWrapper<User> query = Wrappers.<User>query();
        query.like(StringUtils.isNotEmpty(name),"name", "李").lt("age", 40);


        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    @Test
    public void test2() {
        User user = new User();
        user.setName("李静");
        QueryWrapper<User> query = new QueryWrapper<>(user);
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }


    @Test
    public void test3() {
        //重载忽略null
        //fillter
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","李静");
        map.put("age",25);
        QueryWrapper<User> query = new QueryWrapper<User>().allEq(map);
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }
}