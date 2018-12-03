package com.bs.ssh.service.user;

import com.bs.ssh.bean.PageBean;
import com.bs.ssh.bean.PageRequest;

/**
 * 评论服务
 *
 * @author Egan
 * @date 2018/12/3 22:27
 **/
public interface UserCommentService {

    /**
     * 获得一页评论
     *
     * @date 2018/12/3 23:15
     * @param request 分页请求
     * @return java.util.List<com.bs.ssh.entity.Article>
     **/
    PageBean findAllComment(PageRequest request);

    /**
     * 添加评论
     *
     * @date 2018/12/3 22:00
     * @param userId
     * @param articleId
     * @param content
     * @return void
     **/
    void newComment(String userId, Integer articleId, String content);

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
