package com.yuki.demo1.dao;

import com.yuki.demo1.domain.QueryVo;
import com.yuki.demo1.domain.User;
import com.yuki.demo1.domain.UserDiff;

import java.util.List;

public interface UserDao {

//    @Select("select * from user")
    List<User> findAll();


    void saveUser(User user);

    void updateUser(User user);

    void removeUser(Integer id);

    User findById(Integer id);

    List<User> findByUsername(String username);

    List<User> findQueryVo(QueryVo queryVo);

    List<UserDiff> findAllDiff();
}
