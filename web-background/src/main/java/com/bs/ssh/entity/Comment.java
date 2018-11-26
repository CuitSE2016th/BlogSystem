package com.bs.ssh.entity;

import javax.persistence.*;

/**
 * 文章评论实体
 * 
 * @author Egan
 * @date 2018/11/21 19:41
 **/
@Entity
@Table(name = "comment")
public class Comment {
    private Integer id;
    private Integer author;
    private String content;
    private Long createTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "author_id")
    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
