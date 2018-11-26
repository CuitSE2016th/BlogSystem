package com.bs.ssh.beans;

import javax.persistence.*;

/**
 * 文章收藏实体
 *
 * @author Egan
 * @date 2018/11/26 8:51
 **/
@Entity
@Table(name = "star")
public class Star {
    private Integer id;
    private Integer userId;
    private Integer articleId;
    private Long createTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "article_id")
    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
