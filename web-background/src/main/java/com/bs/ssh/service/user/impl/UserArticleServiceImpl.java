package com.bs.ssh.service.user.impl;

import com.bs.ssh.bean.PageBean;
import com.bs.ssh.bean.PageRequest;
import com.bs.ssh.entity.*;

import com.bs.ssh.dao.BaseDao;
import com.bs.ssh.exception.NoSuchEntityException;
import com.bs.ssh.service.user.UserArticleService;
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

    @Override
    public PageBean getAllArticle(PageRequest pageRequest) {

        List<Article> results = articleDao.findAll(pageRequest, "from Article");

        return new PageBean<>(
                pageRequest,
                articleDao.count("Article"),
                results);

    }

    @Override
    public void giveLike(String userId, Integer articleId) {
        Like like = new Like();
        like.setUserId(userId);
        like.setArticleId(articleId);
        like.setCreateTime(System.currentTimeMillis());
        likeDao.insert(like);
    }

    @Override
    public void starArticle(String userId, Integer articleId) {
        Star star = new Star();
        star.setUserId(userId);
        star.setArticleId(articleId);
        star.setCreateTime(System.currentTimeMillis());
        starDao.insert(star);
    }

    @Override
    public void cancelLike(String userId, Integer articleId) {
        Like like = likeDao.findOne(
                "from Like where userId=? AND articleId=?", userId, articleId);
        if(like == null)
            throw new NoSuchEntityException("已取消点赞，请勿重复操作");
        likeDao.delete(like);
    }

    @Override
    public void cancelStar(String userId, Integer articleId) {
        Star star = starDao.findOne(
                "from Star where userId=? AND articleId=?", userId, articleId);
        if(star == null)
            throw new NoSuchEntityException("已取消收藏，请勿重复操作");
        starDao.delete(star);
    }
}
