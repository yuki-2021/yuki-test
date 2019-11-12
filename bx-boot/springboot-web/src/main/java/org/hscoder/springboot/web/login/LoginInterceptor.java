package org.hscoder.springboot.web.login;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.*;

/**
 * 登录拦截器，基于LoginRequired注解检查是否需要登录，如果当期未登录则重定向到登录界面
 * 
 * @author atp
 *
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        LoginRequired loginFlag = handlerMethod.getMethodAnnotation(LoginRequired.class);
        if (loginFlag == null) {
            loginFlag = handlerMethod.getBeanType().getAnnotation(LoginRequired.class);
        }

        if (loginFlag == null) {
            return true;
        }

        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_KEY);
        if (sessionUser == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * 注解
     * 
     * @author atp
     *
     */
    @Target(value = { ElementType.TYPE, ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public static @interface LoginRequired {
        String value() default "";
    }
}