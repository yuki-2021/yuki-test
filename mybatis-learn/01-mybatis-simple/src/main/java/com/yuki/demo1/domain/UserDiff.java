package com.yuki.demo1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserDiff implements Serializable {
    private Integer userid;
    private String username;
    private Date userbirthday;
    private String usersex;
    private String useraddress;
}