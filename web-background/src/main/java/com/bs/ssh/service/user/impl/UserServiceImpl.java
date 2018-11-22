package com.bs.ssh.service.user.impl;

import com.bs.ssh.beans.JsonBody;
import com.bs.ssh.beans.Role;
import com.bs.ssh.beans.User;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.service.user.UserService;
import com.bs.ssh.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public JsonBody<String> login(String identity, String password) {
        User user = userDao.findByIdentity(identity);
        JsonBody<String> body = new JsonBody<>();
        if(user==null ){
            body.setCode(Constants.RESPONSE_FAILED);
            body.setMessage("用户不存在");
        } else if(!user.getPassword().equals(HashUtils.sha256(password, user.getSalt()))){
            body.setCode(Constants.RESPONSE_FAILED);
            body.setMessage("用户名或密码不正确");
        }else {
            body.setCode(Constants.RESPONSE_SUCCEED);
            body.setMessage("登录成功");
            //保存token到redis服务器
            String token = HashUtils.getToken();
            RedisUtils.set(token, JsonUtil.toJsonExposed(user));
            body.setData(token);
        }
        return body;
    }


}
