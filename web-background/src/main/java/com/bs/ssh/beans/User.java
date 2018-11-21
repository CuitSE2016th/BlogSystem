package com.bs.ssh.beans;

import com.google.gson.annotations.Expose;

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
    @Expose private String nickname;
    @Expose private String headPortrait;
    @Expose private Long birthday;
    @Expose private String sex;
    @Expose private String email;
    @Expose private String phone;
    @Expose private String password;
    private String salt;
    @Expose private Role role;
    @Expose private Long lastLoginTime;
    @Expose private Long createTime;
    private List<User> followings;
    private List<User> followers;
    //用户点赞的文章
    private List<Article> likeArticles;
    //用户收藏的文章
    private List<Article> starArticles;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    public Role getRoleID() {
        return role;
    }

    public void setRoleID(Role role) {
        this.role = role;
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


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "liking",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "article_id")}
    )
    public List<Article> getLikeArticles() {
        return likeArticles;
    }

    public void setLikeArticles(List<Article> likeArticles) {
        this.likeArticles = likeArticles;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "star",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "article_id")}
    )
    public List<Article> getStarArticles() {
        return starArticles;
    }

    public void setStarArticles(List<Article> starArticles) {
        this.starArticles = starArticles;
    }



}