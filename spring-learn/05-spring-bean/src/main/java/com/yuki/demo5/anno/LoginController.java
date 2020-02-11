package com.yuki.demo5.anno;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Controller
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class LoginController {

    @Autowired(required = false)
    @Qualifier("loginService")
    private LoginService loginService;


    @Autowired
    private List<Car> cars;


    public void init() {
        System.out.println("init 1");
    }


    public void init2() {
        System.out.println("init 2");
    }

    public void des() {
        System.out.println("des 1");
    }


    public void des2() {
        System.out.println("des 2");
    }
}
