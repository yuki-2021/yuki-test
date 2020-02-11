package com.yuki.demo5.anno;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserController {

    private UserService userService;

    private Car car;

    @PostConstruct
    public void init() {
        System.out.println("init 1");
        System.out.println("init 3");
    }

    @PostConstruct
    public void init2() {
        System.out.println("init 2");
    }

    @PreDestroy
    public void des() {
        System.out.println("des 1");
    }

    @PreDestroy
    public void des2() {
        System.out.println("des 2");
    }
}
