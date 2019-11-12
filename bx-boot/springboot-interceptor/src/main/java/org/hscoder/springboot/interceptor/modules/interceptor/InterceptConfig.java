package org.hscoder.springboot.interceptor.modules.interceptor;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptConfig extends WebMvcConfigurerAdapter {

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new CustomHandlerInterceptor()).addPathPatterns("/intercept/**");
        super.addInterceptors(registry);
    }

    /**
     * 定义Filter配置
     * 
     * @return
     */
    @Bean
    public FilterRegistrationBean customerFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();

        // 设置过滤器
        registration.setFilter(new CustomerFilter());

        // 拦截路由规则
        registration.addUrlPatterns("/intercept/*");

        // 设置初始化参数
        registration.addInitParameter("name", "customFilter");

        registration.setName("CustomerFilter");
        registration.setOrder(1);
        return registration;
    }

}
