package com.bs.ssh.service.root.impl;

import com.bs.ssh.beans.PageBean;
import com.bs.ssh.beans.User;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.service.root.RootService;
import com.bs.ssh.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Create By ZZY on 2018/11/22
 */
@Service
@Transactional
public class RootServiceImpl implements RootService {

    @Autowired
    private UserDao userDao;

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

        users.setResult(JsonUtils.toJsonExposed(userList));

        return users;
    }

    @Override
    public User getUserByIdentity(String identity) {
        return userDao.findByIdentity(identity);
    }

    @Override
    public int updateUserTypeByUserID(String userID, String type) {

        return userDao.updateUserRoleID(userID, type);
    }

}
