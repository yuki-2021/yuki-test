package com.yuki;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yuki.dao.UserMapper;
import com.yuki.entry.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;


/**
 *
 * AR模式 Model
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
//AR 一个模型类对应 一个表
public class ARTest {


    /**
     * AR插入
     *
     */
    @Test
    public void test1(){
        User user = new User();
        user.setName("李雨");
//        user.setAge(21);
        user.setManagerId(1186609160016445441L);
        boolean insert = user.insert();
        System.out.println(insert);
    }

    /**
     *
     * AR查询 Model
     *
     */
    @Test
    public void test2(){
        //两种写法
        User user = new User();
        user.setId(1187032471225516033L);

        User user1 = user.selectById();
        System.out.println(user1.toString());
    }
}
