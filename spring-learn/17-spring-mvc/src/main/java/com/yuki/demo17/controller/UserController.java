package com.yuki.demo17.controller;

import com.yuki.demo17.domain.User;
import com.yuki.demo17.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register() {
        return "user/register";
    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView createUser(User user) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/createSuccess");
        mv.addObject("user",user);
        return mv;
    }

}
