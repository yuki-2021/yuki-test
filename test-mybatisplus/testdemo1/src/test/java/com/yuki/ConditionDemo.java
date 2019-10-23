package com.yuki;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ConditionDemo {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void test1() {
        //列名
        //QueryWrapper<T> tQueryWrapper = new QueryWrapper<T>();
        QueryWrapper<User> query = Wrappers.<User>query();
        query.like("name", "李").lt("age", 40);
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    @Test
    public void test2() {
        QueryWrapper<User> query = Wrappers.<User>query().like("name", "李").between("age", "20", "40").isNotNull("email");
        userMapper.selectList(query).forEach(System.out::println);
    }

    @Test
    public void test3(){
        QueryWrapper<User> query = Wrappers.<User>query().like("name", "李").or().ge("age", 25).orderByDesc("age").orderByAsc("id");
        userMapper.selectList(query).forEach(System.out::println);
    }

    @Test
    public void test4(){
        //ate_format(create_time,'%Y-%m-%d') = 2019-10-05 会有sql注入风险 or true or true
        QueryWrapper<User> query = Wrappers.<User>query().apply("date_format(create_time,'%Y-%m-%d') = {0}", "2019-10-05")
                .inSql("manager_id", "select id from user where name like '李%'");
        userMapper.selectList(query).forEach(System.out::println);
    }

    @Test
    public void test5(){
        QueryWrapper<User> query = Wrappers.<User>query().likeRight("name", "李")
                .and(wq -> wq.lt("age", 40).or().isNotNull("email"));

        userMapper.selectList(query).forEach(System.out::println);
    }

    @Test
    public void test6(){
        QueryWrapper<User> query = Wrappers.<User>query().likeRight("name", "李")
                .or(wq -> wq.lt("age", 40).or().isNotNull("email"));
        userMapper.selectList(query).forEach(System.out::println);
    }

    @Test
    public void test7(){
        QueryWrapper<User> query = Wrappers.<User>query()
                .nested(wq -> wq.lt("age", 40).or().isNotNull("email"))
                .likeRight("name","李");
        userMapper.selectList(query).forEach(System.out::println);
    }

    @Test
    public void test8(){
        QueryWrapper<User> query = Wrappers.<User>query().in("age",Arrays.asList(40));
        userMapper.selectList(query).forEach(System.out::println);
    }

    @Test
    public void test9(){
        QueryWrapper<User> query = Wrappers.<User>query().in("age",Arrays.asList(40)).last("limit 1");
        userMapper.selectList(query).forEach(System.out::println);
    }

    @Test
    public void test10(){
        QueryWrapper<User> query = Wrappers.<User>query().select("id","name").in("age",Arrays.asList(40)).last("limit 1");
        userMapper.selectList(query).forEach(System.out::println);
    }

    @Test
    public void test11(){
        //pricate
        QueryWrapper<User> query = Wrappers.<User>query()
                .select(User.class,info ->!info.getColumn().equals("create_time") && !info.getColumn().equals("manager_id"))
                .in("age",Arrays.asList(40)).last("limit 1");
        userMapper.selectList(query).forEach(System.out::println);
    }

    //实体构造器 会重复
    //SQLCondition
    public void test12(){
        String name = "鲤鱼";
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(name)){
            wrapper.like("name",name);
        }

    }

    @Test
    public void test13(){
        QueryWrapper<User> manager_id = Wrappers.<User>query().select("avg(age) avg_age").groupBy("manager_id").having("sum(age) <{0}", 500);
    }

    @Test
    public void test14(){
//        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
//        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> 雨 = Wrappers.<User>lambdaQuery().like(User::getName, "雨");
    }

}