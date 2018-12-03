package com.bs.ssh.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 文章收藏实体
 *
 * @author Egan
 * @date 2018/11/26 8:51
 **/
@Entity
@Table(name = "star")
public class Star implements Serializable{
    private String userId;
    private Integer articleId;
    private Long createTime;


    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Id
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
