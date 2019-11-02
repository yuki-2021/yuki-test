package com.yuki.param.validator.controller;

import com.yuki.param.validator.annotation.DateTime;
import com.yuki.param.validator.groups.Groups;
import com.yuki.param.validator.pojo.Book;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * 普通参数验证   Java对象验证
 *
 *
 */
//开启数据校验
@Validated
@RestController
public class ValidateController {

    @GetMapping("/test")
    public String test(@NotBlank(message = "name 不能为空") @Length(min = 2, max = 10, message = "name 长度必须在 {min} - {max} 之间") String name){
        return "success";
    }

    @GetMapping("/test2")
    public String test2(@Validated Book book){
        return "success";
    }

    @GetMapping("/test3")
    public String test3(@DateTime(format = "yyyy-MM-dd HH:mm") String date){
        return "success";
    }


    @GetMapping("/insert")
    public String insert(@Validated(value = Groups.Default.class) Book book){
        return "insert success";
    }

    @GetMapping("/update")
    public String update(@Validated(value = {Groups.Default.class,Groups.Update.class}) Book book){
        return "insert success";
    }
}
