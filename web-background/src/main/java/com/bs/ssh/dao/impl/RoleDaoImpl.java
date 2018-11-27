package com.bs.ssh.dao.impl;

import com.bs.ssh.entity.Role;
import com.bs.ssh.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
    @Autowired
    public RoleDaoImpl(HibernateTemplate template) {
        super(template);
    }
}
