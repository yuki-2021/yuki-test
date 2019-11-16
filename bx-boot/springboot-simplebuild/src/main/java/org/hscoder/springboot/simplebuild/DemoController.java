package org.hscoder.springboot.simplebuild;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "你好世界 !!!";
    }


    @RequestMapping("/hello")
    @ResponseBody
    String home1() {
        return "home 1";
    }
}
