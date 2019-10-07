package com.yuki.testdb.beans;

import com.yuki.testdb.converter.SexConverter;

import javax.persistence.*;

@Entity(name = "user")
@Table(name = "t_user")
public class User {

    @Id
    //主键策略 递增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Convert(converter = SexConverter.class)
    private SexEnum sex;

    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", sex=" + sex.getName() +
                ", note='" + note + '\'' +
                '}';
    }
}
