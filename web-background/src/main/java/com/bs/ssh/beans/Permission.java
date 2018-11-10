package com.bs.ssh.beans;

import javax.persistence.*;
import java.sql.Date;

/**
 * 权限类
 *
 * @author Egan
 * @date 2018/11/10 17:55
 **/
@Entity
@SqlResultSetMapping(
        name = "searchResultMapping.ticket",
        entities = { @EntityResult(entityClass = Permission.class) },
        columns = { @ColumnResult(name = "_ft_scoreColumn", type = Double.class) }
)
@Table(name = "permission")
public class Permission {
    private String id;
    private String name;
    private Date createDate;

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

    @Column(name = "create_time")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
