package com.yuki;

import com.yuki.dao.UserMapper;
import com.yuki.entry.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * 使用枚举类
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)

public class EnumTest {

    @Autowired
    private UserMapper userMapper;


    /**
     * 使用枚举类
     *
     */
    @Test
    public void test1(){
        List<User> users = userMapper.selectList(null);
    }
}
