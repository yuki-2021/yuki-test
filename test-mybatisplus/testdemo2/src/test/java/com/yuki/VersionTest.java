package com.yuki;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yuki.dao.UserMapper;
import com.yuki.entry.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Wrapper;


/**
 * 乐观锁测试
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)

public class VersionTest {

    @Autowired
    private UserMapper userMapper;


    /**
     * 乐观锁
     *
     */
    @Test
    public void test1(){
        int versionId = 1;

        User user = new User();
        user.setName("李鱼");
        user.setAge(30);
        user.setId(1088248166370832385L);
        user.setVersion(versionId);

        int i = userMapper.updateById(user);
        System.out.println("影响行数： " + i);
    }

    /**
     * 不能通用wrapper
     *
     */
    @Test
    public void test2(){
        User user = new User();
        user.setAge(31);
        user.setVersion(2);
        QueryWrapper<User> wrapper = Wrappers.<User>query()
                .eq("name", "李鱼");
        int update = userMapper.update(user, wrapper);

        user.setAge(33);
        user.setVersion(3);
        int update1 = userMapper.update(user, wrapper);
    }
}
