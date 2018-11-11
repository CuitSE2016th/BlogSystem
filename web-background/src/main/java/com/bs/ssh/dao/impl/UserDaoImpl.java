package com.bs.ssh.dao.impl;

import com.bs.ssh.beans.User;
import com.bs.ssh.dao.UserDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    private HibernateTemplate template;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.template = new HibernateTemplate(sessionFactory);
    }

    @Override
    public User findUser(String identity) {
        String hql = "from User where email=? or phone=?";
        List<?> users = template.find(hql, identity, identity);

        return users.size()>0 ? (User) users.get(0) : null;
    }
}
