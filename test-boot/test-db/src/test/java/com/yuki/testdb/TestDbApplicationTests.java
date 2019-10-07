package com.yuki.testdb;

import com.yuki.testdb.beans.SexEnum;
import com.yuki.testdb.beans.User;
import com.yuki.testdb.dao.MyDao;
import com.yuki.testdb.dao.UserDao;
import com.yuki.testdb.service.JdbcService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDbApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcService jdbcService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private MyDao myDao;

    @Test
    public void contextLoads() {
        System.out.println(dataSource.getClass().getName());
    }

    //insert
    @Test
    public void test1(){
        User user = new User();
        user.setUserName("sdc");
        user.setSex(SexEnum.MALE);
        user.setNote("sdc note");
        jdbcService.insertUser(user);
    }

    //get
    @Test
    public void test2(){
        User user = jdbcService.getUser(1l);
        System.out.println(user.toString());
    }

    //findUsers
    @Test
    public void test3(){
        List<User> users = jdbcService.findUsers("sdc", "sdc");
        System.out.println(users.size());
        //循环输出
        users.forEach(user -> System.out.println(user));
    }


    //事务
    @Test
    public void test4(){
       jdbcService.statementCallback();
    }

    //jpa
    @Test
    public void test5(){
        User user = userDao.findById(1l).get();
        System.out.println(user.toString());
    }

    //mybatis
    @Test
    public void test6(){
        User user = myDao.getUser(1l);
        System.out.println(user.toString());
    }











}
