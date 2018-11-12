package com.bs.ssh.beans;

import javax.persistence.*;
import java.util.List;

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
    private String nickname;
    private String headPortrait;
    private Long birthday;
    private String sex;
    private String email;
    private String phone;
    private String password;
    private String salt;
    private String roleID;
    private Long lastLoginTime;
    private Long createTime;
    private List<User> followings;
    private List<User> followers;


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

    @Basic
    @Column(name = "role_id")
    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
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

    @ManyToMany(mappedBy = "followers")
    public List<User> getFollowings() {
        return followings;
    }

    public void setFollowings(List<User> following) {
        this.followings = following;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "follow",
            joinColumns = {@JoinColumn(name = "following_id")},
            inverseJoinColumns = {@JoinColumn(name = "follower_id")}
    )
    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> follower) {
        this.followers = follower;
    }

}
