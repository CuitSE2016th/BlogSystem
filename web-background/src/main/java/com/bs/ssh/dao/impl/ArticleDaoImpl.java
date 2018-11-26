package com.bs.ssh.dao.impl;

import com.bs.ssh.entity.Article;
import com.bs.ssh.dao.ArticleDao;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create By ZZY on 2018/11/23
 */
@Repository
public class ArticleDaoImpl extends BaseDaoImpl<Article> implements ArticleDao{

    public ArticleDaoImpl(HibernateTemplate template) {
        super(template);
    }

    @Override
    public int setArticleStatus(int parseInt, int articleStatus) {
        Session currentSession = this.getTemplate().getSessionFactory().getCurrentSession();
        int i = currentSession.createQuery("update Article set status = ? where id = ?")
                .setParameter(0, articleStatus)
                .setParameter(1, parseInt).executeUpdate();
        return i;
    }

    @Override
    public List<Article> getArticlesInclude500(int pn, int pageSize) {
        Session currentSession = this.getTemplate().getSessionFactory().getCurrentSession();

        List<Article> list = currentSession.createQuery("from Article where status = 500")
                .setFirstResult((pn-1) * pageSize)
                .setMaxResults(pageSize).list();

        return list;
    }

    @Override
    public int getArticlesCount() {
        Session currentSession = this.getTemplate().getSessionFactory().getCurrentSession();
        long count = (long) currentSession.createQuery("select count(*) from Article where status = 500").uniqueResult();

        return new Long(count).intValue();
    }


}
