package com.bs.ssh.dao;

import com.bs.ssh.entity.Article;

import java.util.List;

/**
 * Create By ZZY on 2018/11/23
 */
public interface ArticleDao extends BaseDao<Article>{
    int setArticleStatus(int parseInt, int articleStatus);

    List<Article> getArticlesInAdmin(int pn, int pageSize);

    boolean isArticleExisted(Integer articleId);

    Article findArticle(String userId, Integer articleId);

    int getArticlesCount();

    Article findOneByArticleID(int parseInt);
}
