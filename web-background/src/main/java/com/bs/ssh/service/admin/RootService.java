package com.bs.ssh.service.admin;

import com.bs.ssh.beans.JsonBody;
import com.bs.ssh.beans.User;

/**
 * 用户服务
 *
 * @author Egan
 * @date 2018/11/10 20:04
 **/
public interface RootService {

    int registUser(String email, String password);

    int isExistEmail(String emailOrPhone);

    int isExistPhone(String emailOrPhone);
}
