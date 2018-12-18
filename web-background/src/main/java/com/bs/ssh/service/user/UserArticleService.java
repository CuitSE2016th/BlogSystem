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
     * 发布文章
     *
     * @date 2018/12/4 18:40
     * @param userId 用户ID
	 * @param title 标题
	 * @param content 内容
     * @return void
     **/
    Integer publishArticle(String userId, String title, String content);


    /**
     * 删除文章
     * 将文章状态设为ARTICLE_DELETED
     *
     * @author Egan
     * @date 2018/12/4 19:31
     **/
    void deleteArticle(String userId, Integer articleId);

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
