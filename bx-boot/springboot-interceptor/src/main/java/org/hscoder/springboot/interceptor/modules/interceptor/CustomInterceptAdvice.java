package org.hscoder.springboot.interceptor.modules.interceptor;

import org.hscoder.springboot.interceptor.InterceptController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;

@ControllerAdvice(assignableTypes = InterceptController.class)
public class CustomInterceptAdvice {

    private static final Logger logger = LoggerFactory.getLogger(CustomInterceptAdvice.class);

    /**
     * 拦截异常
     * 
     * @param e
     * @param m
     * @return
     */
    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handle(Exception e, HandlerMethod m) {

        logger.info("CustomInterceptAdvice handle exception {}, method: {}", e.getMessage(), m.getMethod().getName());

        return e.getMessage();
    }
}
