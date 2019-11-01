package com.yuki.exception.deal.controller;


import com.yuki.exception.deal.exception.YukiException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class DealController {

    @GetMapping("/test1")
    public String test(){
        int i = 10 /0;
        return "success";
    }

    /**
     * 很难管理业务异常 和 错误码 之间的 匹配关系
     *
     * @return
     */
    @GetMapping("/test2")
    public HashMap<String, String> test2(){
        HashMap<String, String> result = new HashMap<>(16);

        try {
            int i = 10 /0;
            result.put("code","200");
            result.put("data","this is data");
        } catch (Exception e) {
            result.put("code","500");
            result.put("error","请求错误");
        }

        return result;
    }


    /**
     * 全局异常处理
     *
     * @param num
     * @return
     */
    @GetMapping("/test3")
    public String test3(Integer num) throws MethodArgumentNotValidException {
        if(num == null){
            throw  new YukiException(400,"num不能为空");
        }
        int i = 10 /num;
        return "success : " + i;
    }
}
