package com.yuki.testdb.dao;

import com.yuki.testdb.beans.User;
import org.springframework.stereotype.Repository;

@Repository
public interface MyDao {
    //获取用户
    public User getUser(Long id);
}
