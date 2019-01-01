package com.bs.ssh.dao;

import com.bs.ssh.entity.Follow;

public interface FollowDao extends BaseDao<Follow> {

    boolean isFollow(String follower, String following);
}
