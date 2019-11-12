package org.hscoder.springboot.web.login;

import java.util.Date;

/**
 * 会话用户信息
 * 
 * @author atp
 *
 */
public class SessionUser {

    private String username;
    private Date lastLoginTime;
    private String lastLoginIp;

    public static final String SESSION_KEY = "session-user";
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

}
