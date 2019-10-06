package com.yuki.test.aop;

import com.yuki.test.aop.plus.UserValidator;
import com.yuki.test.aop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAopApplicationTests {


    @Autowired
    private UserService userService;
    @Test
    public void contextLoads() {
        UserValidator userValidator = (UserValidator) userService;
        if(userValidator.isReady()){
            this.userService.eat();
        }

    }
}
