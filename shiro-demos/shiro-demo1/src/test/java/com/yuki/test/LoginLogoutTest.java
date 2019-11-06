package com.yuki.test;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.net.PasswordAuthentication;

public class LoginLogoutTest {


    @Test
    public void test(){
        IniSecurityManagerFactory iniSecurityManagerFactory =
                new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager instance = iniSecurityManagerFactory.createInstance();
        SecurityUtils.setSecurityManager(instance);
        //认证信息
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        //验证touken
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        //是否登录成功
        System.out.println(subject.isAuthenticated());
        //登出
        subject.logout();
    }

    @Test
    public void test2(){
        IniSecurityManagerFactory iniSecurityManagerFactory =
                new IniSecurityManagerFactory("classpath:jdbc.ini");
        SecurityManager instance = iniSecurityManagerFactory.createInstance();
        SecurityUtils.setSecurityManager(instance);
        //认证信息
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        //验证touken
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        //是否登录成功
        System.out.println(subject.isAuthenticated());
        System.out.println(subject.getPrincipals().asList().size());
        //登出
        subject.logout();
    }
}
