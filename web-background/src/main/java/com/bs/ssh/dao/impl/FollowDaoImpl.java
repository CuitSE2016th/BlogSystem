package com.bs.ssh.dao.impl;

import com.bs.ssh.dao.FollowDao;
import com.bs.ssh.entity.Follow;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FollowDaoImpl extends BaseDaoImpl<Follow> implements FollowDao {
    public FollowDaoImpl(HibernateTemplate template) {
        super(template);
    }

    @Override
    public boolean isFollow(String follower, String following) {
        return findOne("from Follow where followerId=? and followingId=?",
                follower, following) != null;
    }
}
