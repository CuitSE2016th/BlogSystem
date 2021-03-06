package com.bs.ssh.service.admin;

import com.bs.ssh.bean.PageBean;
import com.bs.ssh.entity.Article;

/**
 * Create By ZZY on 2018/11/23
 */
public interface ArticleAdminService {


    Article getArticleByArticleID(String articleID);

    int setArticleStatus(String articleID, int articleStatus);

    PageBean getArticlesForPage(int pn, int pageSize);
}
