package org.hscoder.springboot.web.login;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * 登录信息
 * 
 * @author atp
 *
 */
public class LoginUser {

    @NotEmpty
    @Length(min = 6, max = 30)
    private String username;

    @NotEmpty
    @Pattern(regexp = "[a-zA-Z0-9_]{6,30}")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
