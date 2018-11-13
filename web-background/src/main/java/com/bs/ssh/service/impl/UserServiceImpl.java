package com.bs.ssh.service.impl;

import com.bs.ssh.beans.JsonBody;
import com.bs.ssh.beans.User;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.service.UserService;
import com.bs.ssh.utils.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public JsonBody<String> login(String identity, String password) {
        User user = userDao.findByIdentity(identity);
        JsonBody<String> body = new JsonBody<>();
        if(user==null||!user.getPassword().equals(HashUtils.sha256(password, user.getSalt()))){
            body.setCode(HttpStatus.UNAUTHORIZED.value());
            body.setMessage("登录失败");
        }else {
            body.setCode(HttpStatus.OK.value());
            body.setMessage("登录成功");
            //保存token到redis服务器
            String token = HashUtils.getToken();
            RedisUtils.set(token, JsonUtil.toJsonExposed(user));
            body.setData(token);
        }
        return body;
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

        user.setRoleID("r001");

        user.setCreateTime(System.currentTimeMillis());
        user.setLastLoginTime(System.currentTimeMillis());

        int flag = userDao.saveUser(user);

        return flag;
    }

}
