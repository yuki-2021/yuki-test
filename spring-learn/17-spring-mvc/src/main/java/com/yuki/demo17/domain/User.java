package com.yuki.demo17.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class User {
    private String userId;
    private String userName;
    private String password;
    private String realName;
    private Date birthday;
    private long salary;
}