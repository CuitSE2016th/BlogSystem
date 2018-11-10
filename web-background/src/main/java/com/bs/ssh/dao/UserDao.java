package com.bs.ssh.dao;

import com.bs.ssh.beans.User;

/**
 * 用户数据访问
 *
 * @author Egan
 * @date 2018/11/10 21:03
 **/
public interface UserDao {

    User findUser(String identity);

}
