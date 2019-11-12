package org.hscoder.springboot.shiro;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.hscoder.springboot.shiro.modules.shiro.ShiroUserManager.UserInfo;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shiro")
public class ShiroController {

    /**
     * 登录界面，展示登录表单
     * 
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "shiro/login";
    }

    /**
     * 登录表单处理
     * 
     * @return
     */
    @PostMapping("/login")
    public String doLogin(HttpServletRequest servletRequest, final RedirectAttributes redirectAttrs) {

        // FormAuthenticationFilter已经做了登录校验处理，
        // 若登录成功会跳转到loginSuccessUrl，这里只做异常处理

        String errorException = (String) servletRequest
                .getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);

        // 登录失败，errorException 非空
        if (!StringUtils.isEmpty(errorException)) {

            // 设置错误消息，执行跳转
            redirectAttrs.addFlashAttribute("loginErrorMsg", "LoginFailed:" + errorException);
            return "redirect:/shiro/login";
        }
        return "OK";
    }

    /**
     * 用户信息界面
     * 
     * @return
     */
    @GetMapping("/user")
    @ResponseBody
    public String user() {

        Subject subject = SecurityUtils.getSubject();
        UserInfo user = (UserInfo) subject.getPrincipals().getPrimaryPrincipal();
        
        return "Welcome back, " + user.getUsername();
    }
    
    /**
     * VIP 用户信息界面
     * 
     * @return
     */
    @GetMapping("/vip")
    @ResponseBody
    public String userVip() {
        Subject subject = SecurityUtils.getSubject();
        UserInfo user = (UserInfo) subject.getPrincipals().getPrimaryPrincipal();
        
        return "Hi, " + user.getUsername() + ", This for the vip";
    }
    
    /**
     * 匿名访问界面
     * 
     * @return
     */
    @GetMapping("/annon/*")
    @ResponseBody
    public String annon() {

        return "this is the content anyone can access";
    }

    /**
     * 无权限界面
     * 
     * @return
     */
    @GetMapping("/unauth")
    @ResponseBody
    public String unauth() {

        return "you are no allow to access";
    }
}
