package org.hscoder.springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 请求头操作
 * 
 * @author atp
 *
 */
@Controller
@RequestMapping("/header")
public class HeaderController {

    private static final Logger logger = LoggerFactory.getLogger(HeaderController.class);

    /**
     * 通过参数注入
     * 
     * @param host
     * @param userAgent
     * @param cacheControl
     * @param response
     * @return
     */
    @GetMapping("/some")
    @ResponseBody
    public String someHeader(@RequestHeader(value = "Host") String host,
            @RequestHeader(value = "User-Agent") String userAgent,
            @RequestHeader(value = "Cache-Control", required = false) String cacheControl,
            HttpServletResponse response) {

        logger.info("host:{}", host);
        logger.info("User-Agent:{}", userAgent);
        logger.info("Cache-Control:{}", cacheControl);

        // 设置响应头
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        return "OK";
    }

    /**
     * 获取全部请求头
     * 
     * @param headers
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, List<String>>> allHeaders(@RequestHeader HttpHeaders headers) {

        Map<String, List<String>> valueMap = new HashMap<String, List<String>>();
        for (String header : headers.keySet()) {
            valueMap.put(header, headers.get(header));
            logger.info("header[{}]={}", header, headers.get(header));
        }

        // 通过ResponseEntity设置响应头
        ResponseEntity<Map<String, List<String>>> entity = ResponseEntity.status(HttpStatus.OK)
                .header("new header", UUID.randomUUID().toString()).body(valueMap);
        return entity;
    }

}
