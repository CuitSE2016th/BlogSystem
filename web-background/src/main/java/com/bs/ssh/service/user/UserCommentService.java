package com.bs.ssh.service.user;

import com.bs.ssh.bean.PageBean;
import com.bs.ssh.bean.PageRequest;
import com.bs.ssh.entity.Comment;

/**
 * 评论服务
 *
 * @author Egan
 * @date 2018/12/3 22:27
 **/
public interface UserCommentService {

    /**
     * 获取一篇文章的一页评论
     *
     * @date 2018/12/4 17:59
     * @param request 
	 * @param articleId   
     * @return com.bs.ssh.bean.PageBean 
     **/  
    PageBean findAllComment(PageRequest request, Integer articleId);

    /**
     * 查看特定评论
     *
     * @author Egan
     * @date 2018/12/4 17:04
     **/
    Comment findOneComment(Integer commentId);

    /**
     * 添加评论
     *
     * @date 2018/12/3 22:00
     * @param userId
     * @param articleId
     * @param content
     * @return void
     **/
    Comment newComment(String userId, Integer articleId, String content);

    /**
     * 删除评论
     *
     * @date 2018/12/3 22:00
     * @param userId
     * @param commentId
     * @return void
     **/
    void deleteComment(String userId, Integer commentId);

    /**
     * 回复评论
     *
     * @date 2018/12/3 22:00
     * @param userId
     * @param parentId 需要回复的评论ID
     * @param content
     * @return void
     **/
    void reply(String userId, Integer parentId, String content);

}
