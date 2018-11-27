package com.bs.ssh.service.root;

import com.bs.ssh.beans.PageBean;
import com.bs.ssh.beans.User;

/**
 * Create By ZZY on 2018/11/22
 */
public interface RootService {

    PageBean getAllUserToPageBean(int pn, int pageSize);

    User getUserByIdentity(String identity);

    int updateUserTypeByUserID(String userID, String type);
}