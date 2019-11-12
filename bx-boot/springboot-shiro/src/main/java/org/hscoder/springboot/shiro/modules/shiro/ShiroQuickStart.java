package org.hscoder.springboot.shiro.modules.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroQuickStart {

    private static final transient Logger log = LoggerFactory.getLogger(ShiroQuickStart.class);

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {

        // 加载 shiro.ini并构造 SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();

        // 设置当前的 SecurityManager对象
        SecurityUtils.setSecurityManager(securityManager);

        // 获取当前用户
        Subject currentUser = SecurityUtils.getSubject();

        // 操作会话
        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            log.info("Retrieved the correct value! [" + value + "]");
        }

        // 执行登录
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                log.info("There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) {
                log.info("The account for username " + token.getPrincipal() + " is locked.  "
                        + "Please contact your administrator to unlock it.");
            } catch (AuthenticationException ae) {
                // unexpected condition? error?
            }
        }

        // 输出用户信息
        log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

        // 检查角色
        if (currentUser.hasRole("schwartz")) {
            log.info("May the Schwartz be with you!");
        } else {
            log.info("Hello, mere mortal.");
        }

        // 检查权限
        if (currentUser.isPermitted("lightsaber:weild")) {
            log.info("You may use a lightsaber ring.  Use it wisely.");
        } else {
            log.info("Sorry, lightsaber rings are for schwartz masters only.");
        }

        // 结束，执行注销
        currentUser.logout();

        System.exit(0);
    }

}
