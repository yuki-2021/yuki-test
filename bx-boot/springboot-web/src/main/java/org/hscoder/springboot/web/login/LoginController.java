package org.hscoder.springboot.web.login;

import org.hscoder.springboot.web.login.LoginInterceptor.LoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录逻辑样例
 * 
 * @author atp
 *
 */
@Controller
@RequestMapping("/login")
@SessionAttributes(SessionUser.SESSION_KEY)
public class LoginController {

    /**
     * 登录页面
     * 
     * @return
     */
    @GetMapping
    public String login() {
        return "login/login";
    }

    /**
     * 登录动作
     * 
     * @param user
     * @return
     */
    @PostMapping
    public String doLogin(@Validated LoginUser user, BindingResult result, RedirectAttributes redirectAttrs,
            HttpServletRequest request, Model model) {

        if (result.hasErrors()) {

            List<String> errorMessages = result.getFieldErrors().stream().map(error -> {
                return error.getField() + error.getDefaultMessage();
            }).collect(Collectors.toList());

            redirectAttrs.addFlashAttribute("flashErrors", errorMessages);
            return "redirect:/login";
        }

        SessionUser sessionUser = new SessionUser();
        sessionUser.setUsername(user.getUsername());
        sessionUser.setLastLoginTime(new Date());
        sessionUser.setLastLoginIp(getClientIp(request));
        model.addAttribute(SessionUser.SESSION_KEY, sessionUser);
        return "redirect:/login/home";
    }

    private String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("x-forward-for");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

    /**
     * 登出动作
     * 
     * @param session
     * @return
     */
    @GetMapping("/out")
    public String logout(HttpSession session) {
        session.removeAttribute(SessionUser.SESSION_KEY);
        return "redirect:/login";
    }

    /**
     * 主界面
     * 
     * @return
     */
    @GetMapping("/home")
    @LoginRequired
    public String home() {
        return "login/home";
    }
}
