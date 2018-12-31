package com.bs.ssh.service.user.impl;

import com.bs.ssh.bean.CommentViewBean;
import com.bs.ssh.bean.PageBean;
import com.bs.ssh.bean.PageRequest;
import com.bs.ssh.dao.ArticleDao;
import com.bs.ssh.dao.CommentDao;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.entity.Comment;
import com.bs.ssh.entity.User;
import com.bs.ssh.exception.NoSuchEntityException;
import com.bs.ssh.service.user.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
public class UserCommentServiceImpl implements UserCommentService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private CommentDao commentBaseDao;
    @Autowired
    private ArticleDao articleDao;

    @Override
    public PageBean findAllComment(PageRequest request, Integer articleId) {
        if (!articleDao.isArticleExisted(articleId))
            throw new NoSuchEntityException("文章不存在");

        List<Comment> parentList = commentBaseDao.findAll(request, "from Comment where article_id=? and is_deleted=? and parent_id=null",
                articleId, false);

        List<CommentViewBean> result = new LinkedList<>();
        for (Comment parent : parentList) {
            User user = userDao.getUserInfoById(parent.getAuthorId());

            if(user == null)
                throw new NoSuchEntityException("评论用户不存在");

            CommentViewBean parentVB = new CommentViewBean(
                    user,
                    parent);

            List<CommentViewBean.Child> childrenVBList = new LinkedList<>();
            for (Comment child :
                    commentBaseDao.findAll("from Comment where parent_id=?",
                            parent.getAuthorId())) {
                CommentViewBean.Child childVB = new CommentViewBean.Child(
                        userDao.getUserInfoById(child.getAuthorId()),
                        child
                );
                childrenVBList.add(childVB);
            }
            result.add(parentVB);
        }


        return new PageBean<>(request, commentBaseDao.count("Comment"), result);
    }

    @Override
    public Comment findOneComment(Integer commentId) {
        Comment comment = commentBaseDao.findOne("from Comment where id=? and is_deleted=?", commentId, false);
        if (comment == null)
            throw new NoSuchEntityException("评论不存在");
        return comment;
    }

    @Override
    public Comment newComment(String userId, Integer articleId, String content) {

        if (!articleDao.isArticleExisted(articleId))
            throw new NoSuchEntityException("文章不存在");

        Comment comment = new Comment(
                articleId, userId, content, System.currentTimeMillis());
        commentBaseDao.insert(comment);
        return comment;
    }

    @Override
    public void deleteComment(String userId, Integer commentId) {
        Comment comment = commentBaseDao.findOne("from Comment where id=? AND author_id=?", commentId, userId);
        if (comment == null)
            throw new NoSuchEntityException("评论不存在或您没有操作权限");

        comment.setDeleted(true);
        commentBaseDao.update(comment);
        List<Comment> children = commentBaseDao.findAll("from Comment where parent_id=?", commentId);
        //软删除子评论
        children.forEach(c -> {
            c.setDeleted(true);
            commentBaseDao.update(c);
        });
    }

    @Override
    public void reply(String userId, Integer parentId, String content) {
        Comment parent = commentBaseDao.findOne("from Comment where id=?", parentId);
        if (parent == null)
            throw new NoSuchEntityException("回复失败，评论不存在");
        Comment comment = new Comment(parentId, userId, content, System.currentTimeMillis());
        comment.setArticleId(parent.getArticleId());
    }

//    private List<CommentViewBean> convertCommentForView(List<Comment> parentList) {
//
//        return parentVBList;
//    }
}
