package com.bs.ssh.dao.impl;

import com.bs.ssh.entity.Article;
import com.bs.ssh.dao.ArticleDao;
import com.bs.ssh.utils.Constants;
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
    public List<Article> getArticlesInAdmin(int pn, int pageSize) {
        Session currentSession = this.getTemplate().getSessionFactory().getCurrentSession();

        List<Article> list = currentSession.createQuery("from Article where status = " + Constants.AUDIT_IN_ADMIN)
                .setFirstResult((pn-1) * pageSize)
                .setMaxResults(pageSize).list();

        return list;
    }

    @Override
    public boolean isArticleExisted(Integer articleId) {

        return this.findOne(
                "from Article where id=? and status=" + Constants.AUDIT_COMPLETE, articleId)
                != null;

    }

    @Override
    public Article findArticle(String userId, Integer articleId) {
        return this.findOne("from Article where authorId=? and id=? and status=" + Constants.AUDIT_COMPLETE, userId, articleId);
    }

    @Override
    public int getArticlesCount() {
        Session currentSession = this.getTemplate().getSessionFactory().getCurrentSession();
        long count = (long) currentSession.createQuery("select count(*) from Article where status = 500").uniqueResult();

        return new Long(count).intValue();
    }

    @Override
    public Article findOneByArticleID(int parseInt) {
        Session currentSession = this.getTemplate().getSessionFactory().getCurrentSession();
        Article o = (Article) currentSession.createQuery("from Article where id = ?").setParameter(0, parseInt).uniqueResult();
        return o;
    }


}
