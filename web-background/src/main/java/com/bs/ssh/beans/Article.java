package com.bs.ssh.beans;

import org.apache.struts2.json.annotations.JSON;

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
    private Integer authorId;
    private Integer status;
    private Long createTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JSON(serialize = false)
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

    @Column(name = "author_id")
    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
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

}
