package com.bs.ssh.service.user.impl;

import com.bs.ssh.bean.PageBean;
import com.bs.ssh.bean.PageRequest;
import com.bs.ssh.entity.*;

import com.bs.ssh.dao.BaseDao;
import com.bs.ssh.service.user.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 *
 * @author Egan
 * @date 2018/11/23 22:13
 **/
@Transactional
@Service
public class UserArticleServiceImpl implements UserArticleService{

    @Resource private BaseDao<Article> articleDao;
    @Resource private BaseDao<Star> starDao;
    @Resource private BaseDao<Like> likeDao;
    @Autowired
    private HibernateTemplate template;

    @Override
    public PageBean getAllArticle(PageRequest pageRequest) {

        List<Article> results = articleDao.findAll(pageRequest, "from Article");

        return new PageBean<>(
                pageRequest,
                articleDao.count("Article"),
                results);

    }

    @Override
    public void giveLike(Integer userId, Integer articleId) {

    }

    @Override
    public void starArticle(Integer userId, Integer articleId) {

    }
}
