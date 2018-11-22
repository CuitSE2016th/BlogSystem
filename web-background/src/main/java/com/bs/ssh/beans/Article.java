package com.bs.ssh.beans;

import javax.persistence.*;
import java.util.List;

/**
 * 文章实体
 *
 * @author Egan
 * @date 2018/11/21 11:27
 **/
@Entity
@Table(name = "article")
public class Article {

    private Integer id;
    private String content;
    private User author;
    private Integer status;
    private Long createTime;
    /**
     * 为本文章点赞的用户
     **/
    private List<User> liker;
    /**
     * 收藏本文章的用户
     **/
    private List<User> collectors;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @ManyToMany(mappedBy = "likeArticles")
    public List<User> getLiker() {
        return liker;
    }

    public void setLiker(List<User> likers) {
        this.liker = likers;
    }

    @ManyToMany(mappedBy = "starArticles")
    public List<User> getCollectors() {
        return collectors;
    }

    public void setCollectors(List<User> collectors) {
        this.collectors = collectors;
    }
}
