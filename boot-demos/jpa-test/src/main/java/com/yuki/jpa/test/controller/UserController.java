package com.yuki.jpa.test.controller;

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


    @GetMapping("/user")
    public List<User> users(){
        return userDao.findAllByName("李雨");
    }
}
