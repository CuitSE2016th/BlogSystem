package com.bs.ssh.dao.impl;

import com.bs.ssh.entity.User;
import com.bs.ssh.dao.AdminDao;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Create By ZZY on 2018/11/22
 */
@Repository
public class AdminDaoImpl extends BaseDaoImpl<User> implements AdminDao{

    @Autowired
    public AdminDaoImpl(HibernateTemplate template) {
        super(template);
    }

    @Override
    public int deleteUserByUserID(String userID) {
        Session currentSession = this.getTemplate().getSessionFactory().getCurrentSession();
        return currentSession.createQuery("delete from User where id = ?")
                .setParameter(0, userID).executeUpdate();
    }
}
