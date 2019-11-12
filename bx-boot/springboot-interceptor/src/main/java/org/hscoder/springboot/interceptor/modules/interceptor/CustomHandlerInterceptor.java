package org.hscoder.springboot.interceptor.modules.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实现 HandlerInterceptor 接口
 * 
 * @author atp
 *
 */
public class CustomHandlerInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(CustomHandlerInterceptor.class);

    /*
     * Controller方法调用前，返回true表示继续处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        logger.info("CustomerHandlerInterceptor preHandle, {}", method.getMethod().getName());

        return true;
    }

    /*
     * Controller方法调用后，视图渲染前
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        HandlerMethod method = (HandlerMethod) handler;
        logger.info("CustomerHandlerInterceptor postHandle, {}", method.getMethod().getName());

        response.getOutputStream().write("append content".getBytes());
    }

    /*
     * 整个请求处理完，视图已渲染。如果存在异常则Exception不为空
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        HandlerMethod method = (HandlerMethod) handler;
        logger.info("CustomerHandlerInterceptor afterCompletion, {}", method.getMethod().getName());

    }

}
