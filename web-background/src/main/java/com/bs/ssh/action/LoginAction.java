package com.bs.ssh.action;

import com.bs.ssh.beans.User;
import com.bs.ssh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 登录动作
 *
 * @author Egan
 * @date 2018/11/11 1:03
 **/
public class LoginAction {

    @Autowired
    private UserService userService;

    private User user;

    public String execute(){
        String identity = user.getPhone() != null ? user.getPhone() : user.getEmail();
        String uid = userService.login(identity, user.getPassword());

        return uid != null ? "success" : "error";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
