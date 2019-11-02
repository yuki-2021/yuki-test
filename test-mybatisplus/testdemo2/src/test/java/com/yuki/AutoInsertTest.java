package com.yuki;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yuki.dao.UserMapper;
import com.yuki.entry.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 自动填充
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)

public class AutoInsertTest {

    @Autowired
    private UserMapper userMapper;


    /**
     * 乐观锁
     *
     */
    @Test
    public void test1(){
        User user = new User();
        user.setName("李AI");
        user.setAge(44);
        user.setManagerId(1087982257332887553L);

        int i = userMapper.insert(user);
        System.out.println("影响行数： " + i);
    }


}
