package com.bs.ssh.dao;

import com.bs.ssh.beans.User;


/**
 * 用户仓库
 *
 * @author Egan
 * @date 2018/11/11 19:47
 **/
public interface UserDao extends BaseDao<User> {

    User findByIdentity(String identity);
}
