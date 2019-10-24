package com.yuki;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yuki.dao.UserMapper;
import com.yuki.entry.User;
import com.yuki.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test1(){
        //第二个参数 设置查出多个情况
        User user = userService.getOne(Wrappers.<User>lambdaQuery().gt(User::getAge, 25),false);
        System.out.println(user);
    }

    @Test
    public void test2(){
        //save or update
        User user = new User();
        user.setName("李玉");
//        user.setAge(11);
        user.setManagerId(1186609160016445441L);

        boolean b = userService.saveBatch(Arrays.asList(user));
        System.out.println(b);
    }

    @Test
    public void test3(){
        userService
                .lambdaQuery()
                .gt(User::getAge,25)
                .like(User::getName,"玉")
                .list()
                .forEach(System.out::println);
    }


    @Test
    public void test4(){
        boolean update = userService
                .lambdaUpdate()
                .eq(User::getAge, 25)
                .set(User::getAge, 26)
                .update();
        System.out.println(update);
    }

}
