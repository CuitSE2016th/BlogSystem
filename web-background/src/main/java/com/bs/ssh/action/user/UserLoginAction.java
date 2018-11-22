package com.bs.ssh.action.user;

import com.bs.ssh.action.BaseAction;
import com.bs.ssh.service.user.UserService;
import com.bs.ssh.utils.RegexString;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
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
@Namespace("user")
@Results({
        @Result(name = "json", type = "json", params = {"root", "result"})
})
public class UserLoginAction extends BaseAction {

    @Autowired
    private UserService userService;

    @Action("login")
    @Validations(
            requiredStrings = {
                    @RequiredStringValidator(fieldName = "identity", message = "邮箱或手机号不能为空"),
                    @RequiredStringValidator(fieldName = "password", message = "密码不能为空")
            },
            regexFields = {
                    @RegexFieldValidator(
                            fieldName = "identity",
                            regexExpression = RegexString.EMAIL_OR_PHONE,
                            message = "邮箱或手机号不合法"
                    ),
                    @RegexFieldValidator(
                            fieldName = "password",
                            regexExpression = RegexString.regex_UserPassword,
                            message = "密码不合法"
                    )
            }
    )
    @InputConfig(methodName = "verify")
    @Override
    public String execute() {

        result = userService.login(identity, password);

        return JSON;
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
