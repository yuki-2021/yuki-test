package com.yuki.lettuce.test.dao;

import org.springframework.cache.annotation.Cacheable;

public class UserDao {

    @Cacheable(value = "user",key = "#key")
    public String get(String key){
        return "aa";
    }
}
