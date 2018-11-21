package com.bs.ssh.beans;

import javax.persistence.*;

/**
 * 文章实体
 *
 * @author Egan
 * @date 2018/11/21 11:27
 **/
@Entity
@Table(name = "article")
public class Article {

    private String id;
    private String content;
    private User author;
    private Long createTime;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
