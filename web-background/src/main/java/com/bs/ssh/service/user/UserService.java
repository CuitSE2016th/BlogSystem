package com.bs.ssh.service.user;

import com.bs.ssh.bean.JsonBody;
import com.bs.ssh.entity.User;

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
    JsonBody<Object> login(String identity, String password);

    /**
     * @param identity 邮箱地址或手机号码
     * @param token 验证信息
     * @return 用户详细信息
     */
    JsonBody<User> getUserInfo(String identity, String token);

    /**
     *
     * @param user 新的用户信息
     * @param token 验证信息
     * @return 操作结果
     */
    JsonBody<User> updateUserInfo(User user, String identity, String token);

    int registUser(String email, String password);

    int isExistEmail(String emailOrPhone);

    int isExistPhone(String emailOrPhone);

    boolean isTokenValid(String token);
}
