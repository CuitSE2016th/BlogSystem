package com.bs.ssh.dao.impl;

import com.bs.ssh.beans.User;
import com.bs.ssh.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
    @Autowired
    public UserDaoImpl(HibernateTemplate template) {
        super(template);
    }

    @Override
    public User findByIdentity(String identity) {
        return this.findOne("FROM User WHERE phone=? or email=?", identity, identity);
    }

    @Override
    public User getUserInfoById(String id) {
        return null;
    }
}
