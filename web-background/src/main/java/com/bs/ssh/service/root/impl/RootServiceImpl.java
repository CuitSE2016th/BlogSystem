package com.bs.ssh.service.root.impl;

import com.bs.ssh.bean.PageBean;
import com.bs.ssh.bean.UserPlus;
import com.bs.ssh.entity.Role;
import com.bs.ssh.entity.User;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.service.root.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By ZZY on 2018/11/222222
 */
@Service
//@Transactional
public class RootServiceImpl implements RootService {

    @Autowired
    private UserDao userDao;

    @Override
    public PageBean getAllUserToPageBean(int pn, int pageSize, int roleID) {

        //构造返回的对象
        PageBean users = new PageBean();
        users.setPageSize(pageSize);

        List userList = null;

        if(roleID == 0){
            userList = userDao.getAllUser(pn, pageSize);
        }else{
            userList = userDao.getAllUserByRoleID(pn, pageSize, roleID);
        }

        if(userList == null){
            return null;
        }

        int recordCount = userList.size();
        //判断当前页是否大于最大页数
        if (pn > recordCount / pageSize){
            pn = recordCount / pageSize;
        }

        users.setRecordCount(recordCount);
        users.setCurrentPage(pn);

        //数据库数据到user加强类的转化
        List<UserPlus> userPlusList = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            Object[] o = (Object[]) userList.get(i);
             UserPlus temp = new UserPlus();
             temp.setUser((User) o[0]);
             temp.setRole((Role) o[1]);
             userPlusList.add(temp);
        }

        users.setResult(userList);

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
