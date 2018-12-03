package com.bs.ssh.dao.impl;

import com.bs.ssh.entity.Role;
import com.bs.ssh.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
    @Autowired
    public RoleDaoImpl(HibernateTemplate template) {
        super(template);
    }

    @Override
    public Set<String> findRoleName(Integer roleId) {
        Set<String> roles = new HashSet<>();
        roles.add(findOne("from Role where id=?", roleId).getName());
        return roles;
    }
}
