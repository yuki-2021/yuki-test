package com.yuki.demo1.test;

import com.yuki.demo1.dao.UserDao;
import com.yuki.demo1.domain.QueryVo;
import com.yuki.demo1.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserDaoTest {

    private SqlSession sqlSession;
    private InputStream in;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSession = new SqlSessionFactoryBuilder().build(in).openSession();
    }

    @After
    public void close() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll() throws IOException {
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> users = userDao.findAll();
        users.stream().forEach(System.out::println);
    }

    @Test
    public void testSaveUser(){
        User user = new User(null, "成妖精", new Date(),
                "男", "啊啊啊啊");
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        userDao.saveUser(user);
        System.out.println(user);

    }

    @Test
    public void testUserUpadte(){
        User user = new User(3, "dog", new Date(),
                "男", "dog home");
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        userDao.updateUser(user);

    }

    @Test
    public void testRemoveUser() {
        Integer id = 4;
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        userDao.removeUser(4);
    }

    @Test
    public void testFindById() {
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.findById(14);
        System.out.println(user);
    }

    @Test
    public void testFindByUsername() {
        String username = "李";
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> users = userDao.findByUsername(username);
        System.out.println(users);
    }

    @Test
    public void testQueryVo() {
        String username = "李";
        User user = new User();
        user.setUsername(username);
        QueryVo queryVo = new QueryVo(user);

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> users = userDao.findQueryVo(queryVo);
        users.stream().forEach(System.out::println);
    }

    @Test
    public void testFindAllDiff() {
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        userDao.findAllDiff().stream().forEach(System.out::println);
    }
}
