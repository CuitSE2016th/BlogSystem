package com.bs.ssh.beans;

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
    private String id;
    private User author;
    private String content;
    private Long createTime;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
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
