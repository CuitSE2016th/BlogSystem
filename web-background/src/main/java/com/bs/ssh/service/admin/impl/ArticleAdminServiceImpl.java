package com.bs.ssh.service.admin.impl;

import com.bs.ssh.beans.Article;
import com.bs.ssh.beans.PageBean;
import com.bs.ssh.dao.ArticleDao;
import com.bs.ssh.service.admin.ArticleAdminService;
import com.bs.ssh.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Create By ZZY on 2018/11/23
 */
@Transactional
@Service
public class ArticleAdminServiceImpl implements ArticleAdminService{

    @Autowired
    private ArticleDao articleDao;


    @Override
    public String getArticleByArticleID(String articleID) {

        Article one = articleDao.findOne("from Article where id = ?", Integer.parseInt(articleID));

        if(one == null){
            return null;
        }

        return JsonUtil.toJsonExposed(one);
    }

    @Override
    public int setArticleStatus(String articleID, int articleStatus) {
        return articleDao.setArticleStatus(Integer.parseInt(articleID), articleStatus);
    }

    @Override
    public PageBean getArticlesForPage(int pn, int pageSize) {

        PageBean pageBean = new PageBean();
        pageBean.setPageSize(15);

        List<Article> articles = articleDao.getArticlesInclude500(pn, pageSize);

        if(articles == null){
            return null;
        }

        if(pn > Math.ceil(articles.size() / pageSize)){
            pn = articles.size() / pageSize;
        }

        pageBean.setRecordCount(articles.size());
        pageBean.setResult(JsonUtil.toJsonExposed(articles));
        pageBean.setCurrentPage(pn);

        return pageBean;
    }
}
