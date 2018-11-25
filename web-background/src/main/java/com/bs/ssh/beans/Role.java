package com.bs.ssh.beans;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

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
    @Expose private String name;
    private Long createTime;
    private List<Permission> permissions;

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
    public Role setId(Integer id) {
        this.id = id;
        return this;
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinTable(
            name = "role_permission",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")}
    )
    @OrderColumn(name = "id")
    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
