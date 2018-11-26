package com.bs.ssh.dao;

import com.bs.ssh.entity.User;

/**
 * Create By ZZY on 2018/11/22
 */
public interface AdminDao extends BaseDao<User> {
    int deleteUserByUserID(String userID);
}
