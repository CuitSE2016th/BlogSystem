package com.bs.ssh.service.admin.impl;

import com.bs.ssh.beans.Role;
import com.bs.ssh.beans.User;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.service.admin.RootService;
import com.bs.ssh.utils.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class RootServiceImpl implements RootService {

    private final UserDao userDao;

    @Autowired
    public RootServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int registUser(String emailOrPhone, String password) {

        User user = new User();

        if (RegexString.ExecRegex(emailOrPhone, RegexString.regex_UserEmail)){
            user.setEmail(emailOrPhone);
        }else{
            user.setPhone(emailOrPhone);
        }

        //获得盐值
        String salt = HashUtils.getSalt();
        user.setSalt(salt);

        //通过SHA256盐值加密
        String sha1Password = HashUtils.sha256(password, salt);
        user.setPassword(sha1Password);

        //获取用户的ID
        String userID = IDUtils.UserID();
        user.setId(userID);

        user.setCreateTime(System.currentTimeMillis());
        user.setLastLoginTime(System.currentTimeMillis());

        int flag = userDao.saveUser(user);

        return flag;
    }

    @Override
    public int isExistEmail(String emailOrPhone) {

        User user = userDao.selectOneByEmail(emailOrPhone);
        return user != null ? 1:0;
    }

    @Override
    public int isExistPhone(String emailOrPhone) {
        User user = userDao.selectOneByPhone(emailOrPhone);
        return user != null ? 1:0;
    }

}
