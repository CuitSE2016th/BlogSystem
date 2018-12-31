package com.bs.ssh.dao.impl;

import com.bs.ssh.bean.IndexArticle;
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
        return this.findOne("FROM User where id = ?", id);
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

    @Override
    public int selectCountArticlePage() {
        Session currentSession = this.getTemplate().getSessionFactory().getCurrentSession();

        long num = (long) currentSession.createQuery("select count(*) from Article").uniqueResult();

        return new Long(num).intValue();
    }

    @Override
    public List<IndexArticle> getArticleforPage(int pn, int pNum) {
        Session currentSession = this.getTemplate().getSessionFactory().getCurrentSession();
//        List list = currentSession.createQuery("SELECT a.id,a.title,a.content, (SELECT  FROM Image i WHERE a.id = i.aid LIMIT 1) as im_url," +
//                "(SELECT COUNT(*) FROM Like l WHERE l.articleId = a.id) as like_count FROM Article a ORDER BY a.createTime DESC ")
//                .setFirstResult((pn - 1) * pNum).setMaxResults(pNum).list();
        List list = currentSession.createSQLQuery("SELECT a.*,(SELECT i.`url` FROM image i WHERE a.`id` = i.`aid` LIMIT 1) im_url, \n" +
                "(SELECT COUNT(*) FROM `like` l WHERE l.`article_id` = a.`id`) like_count, \n" +
                "(SELECT COUNT(*) FROM `comment` c WHERE c.`article_id` = a.`id`) comm_count FROM article a ORDER BY a.`create_time` ").setFirstResult((pn - 1) * pNum).setMaxResults(pNum).list();

        return list;
    }

    @Override
    public List<User> getUserFollows(String userId) {

        List<User> users = (List<User>) getTemplate().find("select u from Follow f left join User u on f.followingId = u.id where f.followerId = ?",
                userId);

        return users;
    }

}
