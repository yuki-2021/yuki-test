package com.yuki.mybatis.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yuki.mybatis.boot.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    List<User> findAllByName(String name);
}
