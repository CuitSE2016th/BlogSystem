package com.bs.ssh.dao.impl;

import com.bs.ssh.dao.CommentDao;
import com.bs.ssh.entity.Comment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao {
    public CommentDaoImpl(HibernateTemplate template) {
        super(template);
    }
}
