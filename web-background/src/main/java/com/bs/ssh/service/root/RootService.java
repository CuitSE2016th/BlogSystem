package com.bs.ssh.service.root;

import com.bs.ssh.bean.PageBean;
import com.bs.ssh.entity.User;

/**
 * Create By ZZY on 2018/11/22
 */
public interface RootService {

    PageBean getAllUserToPageBean(int pn, int pageSize, int roleID);

    User getUserByIdentity(String identity);

    int updateUserTypeByUserID(String userID, String type);
}
