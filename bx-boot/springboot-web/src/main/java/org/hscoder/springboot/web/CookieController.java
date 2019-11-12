package org.hscoder.springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * cookie 读写样例
 * 
 * @author atp
 *
 */
@Controller
@RequestMapping("/cookie")
public class CookieController {

    private static final Logger logger = LoggerFactory.getLogger(CookieController.class);

    /**
     * 通过注解获取
     * 
     * @param counter
     * @param response
     * @return
     */
    @GetMapping("/some")
    @ResponseBody
    public String someCookie(@CookieValue(value = "counter", defaultValue = "0") int counter,
            HttpServletResponse response) {

        logger.info("counter:{}", counter);
        counter += 1;

        String newValue = counter + "";

        // 设置Cookie
        response.addCookie(new Cookie("counter", newValue));
        return newValue;
    }

    /**
     * 获取全部cookie
     * 
     * @param headers
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, String>> allCookies(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> valueMap = new HashMap<String, String>();
        for (Cookie cookie : request.getCookies()) {

            valueMap.put(cookie.getName(), cookie.getValue());
            logger.info("cookie[{}]={}", cookie.getName(), cookie.getValue());
        }

        // 设置Cookie
        response.addCookie(new Cookie("key", UUID.randomUUID().toString()));
        return new ResponseEntity<Map<String, String>>(valueMap, HttpStatus.OK);
    }

    /**
     * 清除全部cookie
     * 
     * @param headers
     * @return
     */
    @GetMapping("/clear")
    public ResponseEntity<Map<String, String>> clearCookies(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> valueMap = new HashMap<String, String>();
        for (Cookie cookie : request.getCookies()) {

            valueMap.put(cookie.getName(), cookie.getValue());
            logger.info("cookie[{}]={}", cookie.getName(), cookie.getValue());

            // 清除
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        return new ResponseEntity<Map<String, String>>(valueMap, HttpStatus.OK);
    }
}
