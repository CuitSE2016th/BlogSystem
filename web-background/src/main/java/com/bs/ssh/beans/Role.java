package com.bs.ssh.beans;

import javax.persistence.*;
import java.sql.Date;

/**
 * 角色实体
 *
 * @author Egan
 * @date 2018/11/10 19:41
 **/
@Entity
@Table(name = "role")
public class Role {
    private String id;
    private String name;
    private Date createTime;


    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
