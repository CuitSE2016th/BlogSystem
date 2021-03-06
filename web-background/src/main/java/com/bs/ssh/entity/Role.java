package com.bs.ssh.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

/**
 * 角色实体
 *
 * @author Egan
 * @date 2018/11/10 19:41
 **/
@Entity
@Table(name = "role")
public class Role {
    private Integer id;
    private String name;
    private Long createTime;

    //默认角色为普通用户
    public Role() {
        this.id = 1;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    //user.setRole(new Role().setId(1));
    public void setId(Integer id) {
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
