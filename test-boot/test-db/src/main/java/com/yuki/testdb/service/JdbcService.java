package com.yuki.testdb.service;

import com.yuki.testdb.beans.User;

import java.util.List;

public interface JdbcService {

    public User getUser(Long id);

    public List<User> findUsers(String userName,String note);

    public int insertUser(User user);

    public int updateUser(User user);

    public int deleteUser(Long id);

    public void statementCallback();
}
