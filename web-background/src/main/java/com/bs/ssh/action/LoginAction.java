package com.bs.ssh.action;

import com.bs.ssh.beans.User;
import com.bs.ssh.service.UserService;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 登录动作类
 *
 * @author Egan
 * @date 2018/11/10 21:27
 **/
@Results(value = {
        @Result(name = "SUCCESS", location = "/login.jsp"),
        @Result(name = "ERROR", location = "/error.jsp")
})
public class LoginAction {
    private UserService userService;
    private User user;

    public String execute(){

        String identity = user.getPhone() != null ? String.valueOf(user.getPhone()) : user.getEmail();
        String uid = userService.login(identity, user.getPassword());

        return uid == null ? "SUCCESS" : "ERROR";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserService getService() {
        return userService;
    }

    public void setService(UserService service) {
        this.userService = service;
    }
}
