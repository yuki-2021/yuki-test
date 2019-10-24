package com.yuki;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yuki.dao.UserMapper;
import com.yuki.entry.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 阻断器测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class InterceporTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1() {
        int delete = userMapper.delete(null);
        System.out.println(delete);
    }

    @Test
    public void test2() {
        int count = userMapper.delete(Wrappers.<User>lambdaQuery().eq(User::getName, "李静"));

        System.out.println("影响行数：" + count);
    }
}
