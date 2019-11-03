package com.yuki.jpa.mtest;


import com.yuki.mybatis.boot.dao.UserDao;
import com.yuki.mybatis.boot.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = JpaTestApplication.class)
public class JpaTestApplicationTest {

    @Autowired
    private UserDao userDao;


    @Test
    public void test(){
        List<User> users = userDao.findAllByName("李雨");
        users.forEach(System.out::println);
    }
}