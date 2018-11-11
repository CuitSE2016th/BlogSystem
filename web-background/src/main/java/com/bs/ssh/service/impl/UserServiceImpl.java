package com.bs.ssh.service.impl;

import com.bs.ssh.beans.User;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.service.UserService;
import com.bs.ssh.utils.HashUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userRepository;

    @Override
    public String login(String identity, String password) {
        User user = userRepository.findByIdentity(identity);
        if(user==null||!user.getPassword().equals(HashUtils.hashBySha1(password + user.getSalt())))
            return null;
        return user.getId();
    }
}
