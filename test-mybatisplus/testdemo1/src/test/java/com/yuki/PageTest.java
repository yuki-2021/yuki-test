package com.yuki;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class PageTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){

        Page<User> page = new Page<>(1, 2);

        QueryWrapper<User> query = Wrappers.<User>query();

        IPage<User> userIPage = userMapper.selectPage(page, query);
        long total = userIPage.getTotal();
        long total1 = userIPage.getTotal();
        userIPage.getRecords().forEach(System.out::println);
    }
}
