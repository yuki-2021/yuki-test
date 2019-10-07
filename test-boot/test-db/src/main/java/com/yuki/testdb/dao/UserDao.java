package com.yuki.testdb.dao;

import com.yuki.testdb.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
}
