package com.bs.ssh.service.impl;

import com.bs.ssh.beans.Token;
import com.bs.ssh.beans.User;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.service.UserService;
import com.bs.ssh.utils.DateUtils;
import com.bs.ssh.utils.HashUtils;

import com.bs.ssh.utils.IDUtils;
import com.bs.ssh.utils.RegexString;
import com.bs.ssh.utils.SHA1Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public String login(String identity, String password) {
        User user = userDao.findByIdentity(identity);
        if(user==null||!user.getPassword().equals(HashUtils.hashBySha1(password + user.getSalt()))){
            return null;
        }
        return user.getId();
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

        //通过SHA1盐值加密
        String sha1Password = SHA1Util.SHA1(password, salt);
        user.setPassword(sha1Password);

        //获取用户的ID
        String userID = IDUtils.UserID();
        user.setId(userID);

        user.setRoleID("r001");

        user.setCreateTime(System.currentTimeMillis());
        user.setLastLoginTime(System.currentTimeMillis());

        //构建Token
        Token token = new Token();
        token.setId(IDUtils.UserTokenID());
        token.setValue(HashUtils.getToken());
        token.setExpireTime(DateUtils.DateAddSomeDay(7));
        token.setCreateTime(System.currentTimeMillis());
        token.setUpdateTime(System.currentTimeMillis());
        token.setUser(user);

        user.setToken(token);

        int flag = userDao.saveUser(user);

        return flag;
    }

}
