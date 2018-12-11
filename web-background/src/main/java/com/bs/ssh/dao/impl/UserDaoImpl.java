package com.bs.ssh.dao.impl;

import com.bs.ssh.bean.UserPlus;
import com.bs.ssh.entity.User;
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

        long count = (long) query.uniqueResult();


        return new Long(count).intValue();
    }

    @Override
    public List getAllUser(int pn, int pageSize) {

        Session currentSession = this.getTemplate().getSessionFactory().getCurrentSession();

        List list = currentSession.createQuery("from User u inner join Role r on u.roleId = r.id")
                .setFirstResult((pn - 1) * pageSize).setMaxResults(pageSize).list();

        return list;
    }

    @Override
    public int updateUserRoleID(String userID, String type) {

        Session currentSession = this.getTemplate().getSessionFactory().getCurrentSession();
        Query query = currentSession.createQuery("update User set roleId = ? where id = ?");
        query.setParameter(0, Integer.parseInt(type));
        query.setParameter(1, userID);
        int i = query.executeUpdate();

        return i;
    }

    @Override
    public User findByIdentityExcludeAdmin(String identity) {
        Session currentSession = this.getTemplate().getSessionFactory().getCurrentSession();
        User o = (User) currentSession.createQuery("from User where roleId = 1 and (email = ? or phone = ?)")
                .setParameter(0, identity)
                .setParameter(1, identity).uniqueResult();
        return o;
    }

    @Override
    public List<UserPlus> getAllUserExcludeAdmin(int pn, int pageSize) {

        Session currentSession = this.getTemplate().getSessionFactory().getCurrentSession();

        List list = currentSession.createQuery("from User u inner join Role r on u.roleId = r.id where u.roleId = 1")
                .setFirstResult((pn - 1) * pageSize).setMaxResults(pageSize).list();

        return list;
    }

    @Override
    public List getAllUserByRoleID(int pn, int pageSize, int roleID) {

        Session currentSession = this.getTemplate().getSessionFactory().getCurrentSession();

        List list = currentSession.createQuery("from User u inner join Role r on u.roleId = r.id where u.roleId = ?")
                .setParameter(0, roleID)
                .setFirstResult((pn - 1) * pageSize).setMaxResults(pageSize).list();

        return list;
    }

}
