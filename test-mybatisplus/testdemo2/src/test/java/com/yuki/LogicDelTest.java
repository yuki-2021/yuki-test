package com.yuki;

import com.yuki.dao.UserMapper;
import com.yuki.entry.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 逻辑删除
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)

public class LogicDelTest {

    @Autowired
    private UserMapper userMapper;


    /**
     * 乐观锁
     *
     */
    @Test
    public void test1(){

        int i = userMapper.deleteById(1094592041087729666L);
        System.out.println("影响行数： " + i);
    }


    @Test
    public void test2(){
        userMapper.selectList(null).forEach(System.out::println);
    }

}
