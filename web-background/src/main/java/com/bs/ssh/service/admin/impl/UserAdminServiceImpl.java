package com.bs.ssh.service.admin.impl;

import com.bs.ssh.bean.PageBean;
import com.bs.ssh.entity.User;
import com.bs.ssh.dao.AdminDao;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.service.admin.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create By ZZY on 2018/11/22
 */
@Service
public class UserAdminServiceImpl implements UserAdminService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private AdminDao adminDao;

    @Override
    public PageBean getAllUserToPageBean(int pn, int pageSize) {

        //构造返回的对象
        PageBean users = new PageBean();
        users.setPageSize(pageSize);

        //查询总记录数
        int recordCount = userDao.getUserCount();

        if(recordCount == 0){
            return null;
        }

        //判断当前页是否大于最大页数
        if (pn > recordCount / pageSize){
            pn = recordCount / pageSize;
        }

        users.setRecordCount(recordCount);
        users.setCurrentPage(pn);


        List<User> userList = userDao.getAllUser(pn, pageSize);

        if(userList.size() == 0 || userList == null){
            return null;
        }

        users.setResult(userList);

        return users;
    }

    @Override
    public int deleteUserByUserID(String userID) {

        return adminDao.deleteUserByUserID(userID);
    }

    @Override
    public User getUserByUserID(String identity) {
        return userDao.findByIdentityExcludeAdmin(identity);
    }
}
