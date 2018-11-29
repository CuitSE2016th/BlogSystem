package com.bs.ssh.dao;

import com.bs.ssh.entity.Article;

import java.util.List;

/**
 * Create By ZZY on 2018/11/23
 */
public interface ArticleDao extends BaseDao<Article>{
    int setArticleStatus(int parseInt, int articleStatus);

    List<Article> getArticlesInclude500(int pn, int pageSize);

    int getArticlesCount();
}
