package com.bs.ssh.dao.impl;

import com.bs.ssh.beans.Permission;
import com.bs.ssh.dao.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements PermissionDao {
    @Autowired
    public PermissionDaoImpl(HibernateTemplate template) {
        super(template);
    }
}
