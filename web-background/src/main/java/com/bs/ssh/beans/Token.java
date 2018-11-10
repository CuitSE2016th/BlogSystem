package com.bs.ssh.beans;

import javax.persistence.*;
import java.sql.Date;

/**
 * 令牌实体
 *
 * @author Egan
 * @date 2018/11/10 19:43
 **/
@Entity
@Table(name = "token")
public class Token {
    private String userID;
    private String value;
    private Date expireTime;
    private Date updateTime;
    private Date createTime;

    @Id
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Basic
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "expire_time")
    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @Basic
    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
