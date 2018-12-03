package com.bs.ssh.service.user;


import com.bs.ssh.bean.PageBean;
import com.bs.ssh.bean.PageRequest;

/**
 * 文章服务
 *
 * @author Egan
 * @date 2018/11/23 20:49
 **/
public interface UserArticleService {

    /**
     * 获取所有文章
     *
     * @date 2018/12/3 21:43
     * @param pageRequest
     * @return com.bs.ssh.bean.PageBean
     **/
    PageBean getAllArticle(PageRequest pageRequest);

    /**
     * 点赞
     *
     * @date 2018/12/3 21:43
     * @param userId
	 * @param articleId
     * @return void
     **/
    void giveLike(String userId, Integer articleId);

    /**
     * 收藏
     *
     * @date 2018/12/3 21:43
     * @param userId
	 * @param articleId
     * @return void
     **/
    void starArticle(String userId, Integer articleId);

    /**
     * 取消点赞
     *
     * @date 2018/12/3 21:43
     * @param userId
	 * @param articleId
     * @return void
     **/
    void cancelLike(String userId, Integer articleId);

    /**
     * 取消收藏
     *
     * @date 2018/12/3 21:59
     * @param userId
	 * @param articleId
     * @return void
     **/
    void cancelStar(String userId, Integer articleId);



}
