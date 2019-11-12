package org.hscoder.springboot.shiro.modules.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     * 定义URL 拦截器链
     * 
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean filter(org.apache.shiro.mgt.SecurityManager securityManager) {
        logger.info("config shiro filter");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 定义URL拦截链，拦截链从上而下匹配处理
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        // 允许匿名用户访问首页
        filterChainDefinitionMap.put("/shiro/index", "anon");

        // 定义注销路径
        filterChainDefinitionMap.put("/shiro/logout", "logout");

        // 所有用户界面都需要身份验证，否则会跳转到loginurl，由FormAuthenticationFilter处理
        filterChainDefinitionMap.put("/shiro/user/**", "authc");

        // 为login路径定义拦截，由FormAuthenticationFilter处理
        filterChainDefinitionMap.put("/shiro/login", "authc");

        // 所有vip路径要求具备vip角色权限
        filterChainDefinitionMap.put("/shiro/vip/**", "roles[vip]");

        // 对于所有shiroan路径一律不拦截
        filterChainDefinitionMap.put("/shiroan/**", "anon");

        // 指定loginurl 路径
        shiroFilterFactoryBean.setLoginUrl("/shiro/login");

        // 登录成功后跳转路径
        shiroFilterFactoryBean.setSuccessUrl("/shiro/user/");

        // for un authenticated
        shiroFilterFactoryBean.setUnauthorizedUrl("/shiro/unauth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        // 自定义filters，可覆盖默认的Filter列表，参考 DefaultFilter
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();

        // 定制logout 过滤，指定注销后跳转到登录页(默认为根路径)
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/shiro/login");

        filters.put("logout", logoutFilter);

        // 定制authc 过滤，指定登录表单参数
        FormAuthenticationFilter authFilter = new FormAuthenticationFilter();
        authFilter.setUsernameParam("username");
        authFilter.setPasswordParam("password");

        filters.put("authc", authFilter);

        shiroFilterFactoryBean.setFilters(filters);

        return shiroFilterFactoryBean;
    }

    /**
     * 注册安全管理器
     * 
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        return securityManager;
    }

    /**
     * 注册 Realm 实现
     * 
     * @return
     */
    @Bean
    public ShiroRealm realm() {
        ShiroRealm realm = new ShiroRealm(userManager());
        return realm;
    }

    /**
     * 注册用户管理
     * 
     * @return
     */
    @Bean
    public ShiroUserManager userManager() {
        return new ShiroUserManager(matcher());
    }

    /**
     * 注册密钥匹配管理
     * 
     * @return
     */
    @Bean
    public ShiroHashMatcher matcher() {
        return new ShiroHashMatcher();
    }
}