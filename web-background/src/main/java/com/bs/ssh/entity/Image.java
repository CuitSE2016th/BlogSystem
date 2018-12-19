package com.bs.ssh.entity;

import javax.persistence.*;

/**
 * 图片资源实体
 *
 * @author Egan
 * @date 2018/11/21 13:20
 **/
@Entity
@Table(name = "image")
public class Image {
    private String id;
    private String uid;
    private Integer aid;
    private String url;
    private Long createTime;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    @Basic
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
