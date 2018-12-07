package com.bs.ssh.dao;

import com.bs.ssh.bean.UserPlus;
import com.bs.ssh.entity.User;

import java.util.List;


/**
 * 用户仓库
 *
 * @author Egan
 * @date 2018/11/11 19:47
 **/
public interface UserDao extends BaseDao<User> {

    User findByIdentity(String identity);

    /**
     *
     * @param id 用户唯一识别码
     * @return 用户信息
     */
    User getUserInfoById(String id);

    int saveUser(User user);

    User selectOneByEmail(String emailOrPhone);

    User selectOneByPhone(String emailOrPhone);

    int getUserCount();

    List getAllUser(int pn, int pageSize);


    int updateUserRoleID(String userID, String type);


    User findByIdentityExcludeAdmin(String identity);

    List<UserPlus> getAllUserExcludeAdmin(int pn, int pageSize);

    List getAllUserByRoleID(int pn, int pageSize, int roleID);
}
