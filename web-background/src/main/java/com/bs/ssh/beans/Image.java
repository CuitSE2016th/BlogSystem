package com.bs.ssh.beans;

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
    private String url;
    private Long createTime;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
