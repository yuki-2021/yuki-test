package com.yuki.mybatis.boot.controller;

import com.yuki.mybatis.boot.dao.UserDao;
import com.yuki.mybatis.boot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/user1")
    public List<User> user1(){
        return userDao.findAllByName("李雨");
    }

    @GetMapping("/user2")
    public List<User> user2(){
        User user = new User();
        user.setName("李雨");
        return userDao.findByName(user);
    }
}
