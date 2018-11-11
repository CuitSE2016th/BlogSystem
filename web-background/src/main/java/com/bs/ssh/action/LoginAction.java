package com.bs.ssh.action;

import com.bs.ssh.beans.ResponseBody;
import com.bs.ssh.beans.User;
import com.bs.ssh.service.UserService;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 登录动作
 *
 * @author Egan
 * @date 2018/11/11 1:03
 **/
@ParentPackage("json-default")
@Namespace("/")
@Results({
        @Result(name = "json",type="json", params={"root","result"})
})
public class LoginAction {

    @Autowired
    private UserService userService;

    private User user;

    private ResponseBody<String> result;

    @Action("login")
    public String execute(){
        String identity = user.getPhone() != null ? user.getPhone() : user.getEmail();
        String uid = userService.login(identity, user.getPassword());

        result = new ResponseBody<>();
        result.setCode(200);
        result.setMessage(uid!=null?"success":"failed");

        return "json";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ResponseBody<String> getResult() {
        return result;
    }

    public void setResult(ResponseBody<String> result) {
        this.result = result;
    }
}
