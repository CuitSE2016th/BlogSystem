package com.bs.ssh.service.admin;

import com.bs.ssh.bean.PageBean;
import com.bs.ssh.entity.User;

/**
 * Create By ZZY on 2018/11/22
 */
public interface UserAdminService {
    PageBean getAllUserToPageBean(int pn, int pageSize);

    int deleteUserByUserID(String userID);

    User getUserByUserID(String identity);
}
