package com.bs.ssh.action;

import com.bs.ssh.beans.JsonBody;
import com.bs.ssh.beans.User;
import com.bs.ssh.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 登录动作
 *
 * @author Egan
 * @date 2018/11/11 1:03
 **/
@ParentPackage("json-default")
@Results({
        @Result(name = "json", type = "json", params = {"root", "result"})
})
public class LoginAction extends BaseAction{

    @Autowired
    private UserService userService;

    @Action("login")
    @Validations(
            requiredStrings = {
                    @RequiredStringValidator(fieldName = "identity", message = "邮箱或手机号不能为空"),
                    @RequiredStringValidator(fieldName = "password", message = "密码不能为空")
            }
    )
    @InputConfig(methodName = "verify")
    @Override
    public String execute() {

        String uid = userService.login(identity, password);

        result.setCode(200);
        result.setMessage(uid != null ? "登录成功" : "登录失败");

        return "json";
    }

    private String identity;
    private String password;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
