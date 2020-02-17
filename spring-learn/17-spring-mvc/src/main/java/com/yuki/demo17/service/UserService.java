package com.yuki.demo17.service;

import com.yuki.demo17.domain.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    public static Map<String,User> USERS = new HashMap<String, User>();
    {
        USERS.put("1", new User("1","yuki","123456","yuki",
                new Date(),1500l));
        USERS.put("2", new User("2","sdc","123456","sdc",
                new Date(),1500l));
    }


    public User findUserById(String id) {
        return USERS.get(id);
    }
}
