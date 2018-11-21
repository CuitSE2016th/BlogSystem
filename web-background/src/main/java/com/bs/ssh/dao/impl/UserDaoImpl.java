package com.bs.ssh.dao.impl;

import com.bs.ssh.beans.User;
import com.bs.ssh.dao.UserDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

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

    @Override
    public int saveUser(User user) {

        Session session = this.getTemplate().getSessionFactory().getCurrentSession();

        Serializable save = session.save(user);

        if(save == null){
            return 0;
        }

        return 1;
    }

    @Override
    public User selectOneByEmail(String emailOrPhone) {

        return this.findOne("from User where email = ?", emailOrPhone);

    }

    @Override
    public User selectOneByPhone(String emailOrPhone) {

        return this.findOne("from User where phone = ?", emailOrPhone);
    }

    @Override
    public int getUserCount() {

        Session currentSession = this.getTemplate().getSessionFactory().getCurrentSession();

        Query query = currentSession.createQuery("select count(*) from User");

        int count = (int) query.uniqueResult();

        return count;
    }

    @Override
    public List<User> getAllUser() {

        Session currentSession = this.getTemplate().getSessionFactory().getCurrentSession();

        List<User> from_user = currentSession.createQuery("from User").list();

        return from_user;
    }

}
