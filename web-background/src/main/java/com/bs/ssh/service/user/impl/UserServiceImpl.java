package com.bs.ssh.service.user.impl;

import com.bs.ssh.bean.JsonBody;
import com.bs.ssh.entity.User;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.service.user.UserService;
import com.bs.ssh.utils.Constants;
import com.bs.ssh.utils.HashUtils;
import com.bs.ssh.utils.IDUtils;
import com.bs.ssh.utils.RegexString;
import com.bs.ssh.utils.StringUtils;

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
    public JsonBody<Object> login(String identity, String password) {
        User user = userDao.findByIdentity(identity);
        JsonBody<Object> body = new JsonBody<>();
        if (user == null) {
            body.setCode(Constants.RESPONSE_FAILED);
            body.setMessage("用户不存在");
        } else if (!user.getPassword().equals(HashUtils.sha256(password, user.getSalt()))) {
            body.setCode(Constants.RESPONSE_FAILED);
            body.setMessage("用户名或密码不正确");
        } else {
            body.setCode(Constants.RESPONSE_SUCCEED);
            body.setMessage("登录成功");
            //保存token到redis服务器
            String token = HashUtils.getToken();
            body.setData(token);
            user.setLastLoginTime(System.currentTimeMillis());
            userDao.update(user);
        }
        return body;
    }

    @Override
    public JsonBody<User> getUserInfo(String identity, String token) {
        User user = userDao.findByIdentity(identity);
        JsonBody<User> body = new JsonBody<>();
        if (user == null) {
            body.setCode(Constants.RESPONSE_FAILED);
            body.setMessage("用户不存在");
        } else if (!isTokenValid(token)) {
            body.setCode(Constants.RESPONSE_FAILED);
            body.setMessage("Token 出错");
        } else {
            body.setCode(Constants.RESPONSE_SUCCEED);
            body.setMessage("SUCCESS");
            body.setData(user);
        }
        return body;
    }

    @Override
    public JsonBody<User> updateUserInfo(User tempUser, String identity, String token) {
        User user = userDao.findByIdentity(identity);
        JsonBody<User> body = new JsonBody<>();
        if (!isTokenValid(token)) {
            body.setCode(Constants.RESPONSE_FAILED);
            body.setMessage("token is error");
        } else if (user == null) {
            body.setCode(Constants.RESPONSE_FAILED);
            body.setMessage("The user does not exist.");
        } else {
            if (StringUtils.isNotBlank(tempUser.getNickname())) {
                user.setNickname(tempUser.getNickname());
            }

            if (StringUtils.isNotBlank(tempUser.getSex())) {
                user.setSex(tempUser.getSex());
            }

            if (StringUtils.isNotBlank(tempUser.getHeadPortrait())) {
                user.setHeadPortrait(tempUser.getHeadPortrait());
            }
            userDao.insert(user);
            body.setCode(Constants.RESPONSE_SUCCEED);
            body.setData(user);
        }
        return body;
    }

    @Override
    public int registUser(String email, String password) {
        User user = new User();

        if (RegexString.ExecRegex(email, RegexString.regex_UserEmail)) {
            user.setEmail(email);
        } else {
            user.setPhone(email);
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

        user.setRoleId(1);

        user.setCreateTime(System.currentTimeMillis());
        user.setLastLoginTime(System.currentTimeMillis());

        int flag = userDao.saveUser(user);

        return flag;
    }

    @Override
    public int isExistEmail(String emailOrPhone) {
        User user = userDao.selectOneByEmail(emailOrPhone);
        return user != null ? 1 : 0;
    }

    @Override
    public int isExistPhone(String emailOrPhone) {
        User user = userDao.selectOneByPhone(emailOrPhone);
        return user != null ? 1 : 0;
    }

    @Override
    public boolean isTokenValid(String token) {
        return token != null;
    }


}
