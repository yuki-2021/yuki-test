package org.hscoder.springboot.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;

/**
 * 基于注解的样例
 * 
 * @author atp
 *
 */
@Controller
@RequestMapping("/shiroan")
public class ShiroAnnotateController {

    private static final Logger logger = LoggerFactory.getLogger(ShiroAnnotateController.class);

    /**
     * 模拟登录接口
     * 
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {

        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(username, password.toCharArray());

        try {
            // 执行登录
            subject.login(token);

        } catch (UnknownAccountException e) {

            // 未知用户
            logger.warn("the account {}  is not found", username);

            return "account not found";
        } catch (IncorrectCredentialsException e) {

            // 用户或密码不正确
            logger.warn("the account or password is not correct");
            return "account or password not correct";

        }
        return "login success";
    }

    @RequestMapping("/logout")
    @ResponseBody
    public String logout() {

        Subject subject = SecurityUtils.getSubject();

        // 执行注销
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return "OK";
    }

    /**
     * vip 界面，需要vip角色
     * 
     * @return
     */
    @RequiresRoles("vip")
    @GetMapping("/vip")
    @ResponseBody
    public String vip() {

        return "this is the vip info";
    }

    /**
     * home 界面，需要登录
     * 
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/home")
    @ResponseBody
    public String home() {

        return "this is the home page";
    }

    /**
     * 资料界面，需要资料权限
     * 
     * @return
     */
    @RequiresPermissions("customer.profile.read")
    @GetMapping("/profile")
    @ResponseBody
    public String profile() {

        return "this is the profile info";
    }

    /**
     * 读取相册界面，需要详情权限
     * 
     * @return
     */
    @RequiresPermissions("customer.album.read")
    @GetMapping("/album")
    @ResponseBody
    public String album() {

        return "this is the album info";
    }

    /**
     * 未授权界面
     * 
     * @return
     */
    @GetMapping("/unauth")
    @ResponseBody
    public String unauth() {

        return "you have no authorization";
    }

    /**
     * 自定义拦截，处理鉴权异常
     * 
     * @author atp
     *
     */
    @ControllerAdvice(assignableTypes = ShiroAnnotateController.class)
    public static class AuthExceptionHandler {

        @ExceptionHandler(value = { AuthorizationException.class })
        public ResponseEntity<String> handle(AuthorizationException e, HandlerMethod m) {

            logger.info("Authorization Failed {} -- {}", e.getClass(), e.getMessage());

            String msg = "not allow to access";
            if (e instanceof UnauthorizedException) {

                // 没有权限
                msg = "you have no permissions";
            } else if (e instanceof UnauthenticatedException) {

                // 未登录
                msg = "you must login first";
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(msg);
        }

    }
}
