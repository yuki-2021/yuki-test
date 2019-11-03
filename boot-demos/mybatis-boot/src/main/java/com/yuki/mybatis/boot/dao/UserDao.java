package com.yuki.mybatis.boot.dao;

import com.yuki.mybatis.boot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserDao{

    @Select("select * from test.user where name = '李雨'")
    List<User> findAllByName(@Param("name") String name);


    List<User> findByName(User user);
}
