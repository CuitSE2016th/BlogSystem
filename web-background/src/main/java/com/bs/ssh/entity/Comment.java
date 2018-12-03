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
    private Integer articleId;
    private Integer parentId;
    private String authorId;
    private String content;
    private boolean isDeleted;
    private Long createTime;

    public Comment() { }

    public Comment(Integer articleId, String authorId, String content, Long createTime) {
        this.articleId = articleId;
        this.authorId = authorId;
        this.content = content;
        this.createTime = createTime;
    }

    public Comment(String authorId, Integer parentId, String content, Long createTime) {
        this.parentId = parentId;
        this.authorId = authorId;
        this.content = content;
        this.createTime = createTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "article_id")
    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    @Column(name = "parent_id")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Column(name = "author_id")
    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "is_deleted")
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
