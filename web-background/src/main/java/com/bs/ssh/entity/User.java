package com.bs.ssh.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

/**
 * 用户实体
 *
 * @author Egan
 * @Long 2018/11/10 19:34
 **/
@Entity
@Table(name = "user")
public class User{
    private String id;
    @Expose private String nickname;
    @Expose private String headPortrait;
    @Expose private Long birthday;
    @Expose private String sex;
    @Expose private String email;
    @Expose private String phone;
    @Expose private String password;
    private String salt;
    @Expose private Integer roleId;
    @Expose private Long lastLoginTime;
    @Expose private Long createTime;


    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "head_portrait")
    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    @Basic
    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    @Basic
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Column(name = "role_id")
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "last_login_time")
    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Basic
    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public User() {
    }

    public User(String nickname, String headPortrait, String sex) {
        this.nickname = nickname;
        this.headPortrait = headPortrait;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", roleId=" + roleId +
                ", lastLoginTime=" + lastLoginTime +
                ", createTime=" + createTime +
                '}';
    }
}