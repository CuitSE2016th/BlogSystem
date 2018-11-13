package com.bs.ssh.service;

import com.bs.ssh.beans.JsonBody;
import com.bs.ssh.beans.User;

/**
 * 用户服务
 *
 * @author Egan
 * @date 2018/11/10 20:04
 **/
public interface UserService {

    /**
     * 登录服务
     *
     * @date 2018/11/10 22:10
     * @param identity 邮箱地址或手机号码
	 * @param password 密码
     * @return 登录结果
     **/
    JsonBody<String> login(String identity, String password);

    int registUser(String email, String password);

}
