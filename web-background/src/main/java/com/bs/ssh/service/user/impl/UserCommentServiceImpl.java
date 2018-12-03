package com.bs.ssh.service.user.impl;

import com.bs.ssh.bean.PageBean;
import com.bs.ssh.bean.PageRequest;
import com.bs.ssh.dao.BaseDao;
import com.bs.ssh.entity.Comment;
import com.bs.ssh.exception.NoSuchEntityException;
import com.bs.ssh.service.user.UserCommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class UserCommentServiceImpl implements UserCommentService {

    @Resource private BaseDao<Comment> commentBaseDao;

    @Override
    public PageBean findAllComment(PageRequest request) {
        List<Comment> result = commentBaseDao.findAll(request, "from Comment");

        return new PageBean<>(request, commentBaseDao.count("Comment"), result);
    }

    @Override
    public void newComment(String userId, Integer articleId, String content) {
        Comment comment = new Comment(
                articleId, userId, content, System.currentTimeMillis());
        commentBaseDao.insert(comment);
    }

    @Override
    public void deleteComment(String userId, Integer commentId) {
        Comment comment = commentBaseDao.findOne("from Comment where id=? AND userId=?", commentId, userId);
        if(comment == null)
            throw new NoSuchEntityException("评论不存在或您没有操作权限");

        comment.setDeleted(true);
        commentBaseDao.update(comment);
        List<Comment> children = commentBaseDao.findAll("from Comment where parentId=?", commentId);
        //软删除子评论
        children.forEach(c -> {
            c.setDeleted(true);
            commentBaseDao.update(c);});
    }

    @Override
    public void reply(String userId, Integer parentId, String content) {
        Comment parent = commentBaseDao.findOne("from Comment where id=?", parentId);
        if(parent == null)
            throw new NoSuchEntityException("回复失败，评论不存在");
        Comment comment = new Comment(parentId, userId, content, System.currentTimeMillis());
        comment.setArticleId(parent.getArticleId());
    }
}
