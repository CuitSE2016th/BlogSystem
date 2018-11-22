package com.bs.ssh.service.impl;

import com.bs.ssh.beans.PageBean;
import com.bs.ssh.beans.Role;
import com.bs.ssh.beans.User;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.service.RootService;
import com.bs.ssh.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create By ZZY on 2018/11/22
 */
@Service
public class RootServiceImpl implements RootService{

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

        users.setResult(JsonUtil.toJsonExposed(userList));

        return users;
    }

    @Override
    public User getUserByIdentity(String identity) {
        return userDao.findByIdentity(identity);
    }

}
