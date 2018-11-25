package com.bs.ssh.service.user.impl;

import com.bs.ssh.beans.JsonBody;
import com.bs.ssh.beans.Role;
import com.bs.ssh.beans.User;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.service.UserService;
import com.bs.ssh.utils.Constants;
import com.bs.ssh.utils.HashUtils;
import com.bs.ssh.utils.IDUtils;
import com.bs.ssh.utils.JsonUtil;
import com.bs.ssh.utils.RedisUtils;
import com.bs.ssh.utils.RegexString;
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
            user.setLastLoginTime(System.currentTimeMillis());
            userDao.update(user);
        }
        return body;
    }

    @Override
    public int registUser(String email, String password) {
        User user = new User();

        if (RegexString.ExecRegex(email, RegexString.regex_UserEmail)){
            user.setEmail(email);
        }else{
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

        Role role = new Role();
        user.setRole(role.setId(1));

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
