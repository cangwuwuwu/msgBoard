package com.cangwu.domain;

/**
 * @Author: Cangwu
 * @Date: 2020/3/23 20:27
 */
public class User {

    private Integer id;
    private String email;
    private String password;
    private String repassword;
    private String number;
    private String username;
    private String sex;
    private String phonenum;
    private String birthday;
    private String code;
    private String natives;
    private String intro;
    private String headimg;
    private Integer auth;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repassword='" + repassword + '\'' +
                ", number='" + number + '\'' +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", birthday='" + birthday + '\'' +
                ", code='" + code + '\'' +
                ", natives='" + natives + '\'' +
                ", intro='" + intro + '\'' +
                ", headimg='" + headimg + '\'' +
                ", auth=" + auth +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNatives() {
        return natives;
    }

    public void setNatives(String natives) {
        this.natives = natives;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }
}
