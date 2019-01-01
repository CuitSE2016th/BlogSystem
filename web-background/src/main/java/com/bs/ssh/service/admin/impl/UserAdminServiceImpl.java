package com.bs.ssh.service.admin.impl;

import com.bs.ssh.bean.PageBean;
import com.bs.ssh.bean.UserPlus;
import com.bs.ssh.entity.Role;
import com.bs.ssh.entity.User;
import com.bs.ssh.dao.AdminDao;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.service.admin.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By ZZY on 2018/11/22
 */
@Transactional
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

        users.setCurrentPage(pn);

        List<?> userList = userDao.getAllUserExcludeAdmin(pn, pageSize);

        if(userList == null){
            return null;
        }

        int recordCount = userDao.getUserCount();

        //判断当前页是否大于最大页数
        if (pn > Math.ceil(recordCount / pageSize)){
            pn = recordCount / pageSize;
        }

        //数据库数据到user加强类的转化
        List<UserPlus> userPlusList = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            Object[] o = (Object[]) userList.get(i);
            User user = (User) o[0];
            UserPlus temp = new UserPlus(user.getId(), user.getNickname(), user.getHeadPortrait()
                    ,user.getBirthday(), user.getSex(), user.getEmail(),user.getPhone(),
                    user.getPassword(),user.getSalt(),user.getRoleId(), user.getLastLoginTime(),user.getCreateTime());
            temp.setRole((Role) o[1]);
            userPlusList.add(temp);
        }

        users.setResult(userPlusList);

        users.setRecordCount(recordCount);

        return users;
    }

    @Override
    public int deleteUserByUserID(String userID) {

        return adminDao.deleteUserByUserID(userID);
    }

    @Override
    public User getUserByUserID(String identity) {
        User byIdentityExcludeAdmin = userDao.findByIdentityExcludeAdmin(identity);
        if(byIdentityExcludeAdmin == null){
            return null;
        }

        return byIdentityExcludeAdmin;
    }
}
