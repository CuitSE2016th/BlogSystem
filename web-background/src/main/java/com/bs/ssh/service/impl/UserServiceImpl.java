package com.bs.ssh.service.impl;

import com.bs.ssh.beans.User;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.service.UserService;
import com.bs.ssh.utils.HashUtils;

import com.bs.ssh.utils.IDUtils;
import com.bs.ssh.utils.SHA1Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public String login(String identity, String password) {
        User user = userDao.findByIdentity(identity);
        if(user==null||!user.getPassword().equals(HashUtils.hashBySha256(password + user.getSalt()))){
            return null;
        }
        return user.getId();
    }

    @Override
    public int registUser(String email, String password) {

        User user = new User();
        user.setEmail(email);

        //通过SHA1盐值加密
        String sha1Password = SHA1Util.SHA1(password, email);
        user.setPassword(sha1Password);

        //获取用户的ID
        String userID = IDUtils.UserID();

        user.setId(userID);



        return 0;
    }

}
