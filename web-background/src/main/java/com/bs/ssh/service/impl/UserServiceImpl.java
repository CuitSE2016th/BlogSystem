package com.bs.ssh.service.impl;

import com.bs.ssh.beans.JsonBody;
import com.bs.ssh.beans.PageBean;
import com.bs.ssh.beans.Role;
import com.bs.ssh.beans.User;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.service.UserService;
import com.bs.ssh.utils.*;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public JsonBody<Object> login(String identity, String password) {
        User user = userDao.findByIdentity(identity);
        JsonBody<Object> body = new JsonBody<>();
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

        /* 此处是后面修改的*/
        Role role = new Role();
        role.setId("r001");

        user.setRoleID(role);

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
