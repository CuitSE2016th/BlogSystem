package com.bs.ssh.action.user;

import com.bs.ssh.beans.JsonBody;
import com.bs.ssh.service.root.impl.RootServiceImpl;
import com.bs.ssh.service.user.impl.UserServiceImpl;
import com.bs.ssh.utils.RedisUtils;
import com.bs.ssh.utils.RegexString;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create By ZZY on 2018/11/9
 */
@ParentPackage("json-default")
@Results({
        @Result(name = "success", type = "json", params = {"root", "message"})
})
public class UserRegistAction extends ActionSupport {

    @Autowired
    private UserServiceImpl userService;

    private JsonBody message = null;
    private String emailOrPhone;
    private String password;
    private String emailOrPhoneCode;

    public JsonBody getMessage() {
        return message;
    }

    public void setMessage(JsonBody message) {
        this.message = message;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailOrPhone() {
        return emailOrPhone;
    }

    public void setEmailOrPhone(String emailOrPhone) {
        this.emailOrPhone = emailOrPhone;
    }

    public String getEmailOrPhoneCode() {
        return emailOrPhoneCode;
    }

    public void setEmailOrPhoneCode(String emailOrPhoneCode) {
        this.emailOrPhoneCode = emailOrPhoneCode;
    }

    @Action(value = "userRegist")
    public String userRegist() {

        if (emailOrPhone == null || emailOrPhoneCode == null || password == null ||
        "".equals(emailOrPhone.trim()) || "".equals(emailOrPhoneCode.trim()) || "".equals(password.trim())){
            message = JsonBody.fail();
            message.setMessage("前段数据出错");
            return SUCCESS;
        }

        //如果验证是否为邮箱或者密码
        if (!RegexString.ExecRegex(emailOrPhone, RegexString.regex_UserEmail) &&
                !RegexString.ExecRegex(emailOrPhone, RegexString.regex_UserPhone)) {
            message = JsonBody.fail();
            message.setMessage("前端数据出错");
            return SUCCESS;
        }

        //对于邮箱后缀进行处理
        if(RegexString.ExecRegex(emailOrPhone, RegexString.regex_UserEmail)){
            String[] split = emailOrPhone.split("@");
            String email_suffer_upper = split[1].toLowerCase();
            emailOrPhone = split[0] + "@" + email_suffer_upper;
        }

        //check email or phone wether exist

        int flag = 0;
        if (ExecRegex(emailOrPhone, RegexString.regex_UserEmail)) {
            flag = userService.isExistEmail(emailOrPhone);
        } else {
            flag = userService.isExistPhone(emailOrPhone);
        }

        if (flag == 1) {
            message = JsonBody.fail();
            message.setMessage("账号已经存在");
            return SUCCESS;
        }

        /**
         * 验证密码是否合法
         */
        if (!ExecRegex(password, RegexString.regex_UserPassword)) {
            message = JsonBody.fail();
            message.setMessage("密码不合规格");
            return SUCCESS;
        }

        //在后台得到我们发送给前端的数据
        String Code_after = (String) RedisUtils.get(emailOrPhone);

        if (Code_after == null || "".equals(Code_after)) {
            message = JsonBody.fail();
            message.setMessage("不能得到验证码");
            return SUCCESS;
        }

        //比较前后端验证码是否相同
        if (!Code_after.equals(emailOrPhoneCode)) {
            message = JsonBody.fail();
            message.setMessage("验证码出错");
            return SUCCESS;
        }


        int flag_save = userService.registUser(emailOrPhone, password);

        if (flag_save == 0) {
            message = JsonBody.fail();
            ServletActionContext.getRequest().getSession().removeAttribute(emailOrPhone);
            message.setMessage("数据保存出错");
            return SUCCESS;
        }

        ServletActionContext.getRequest().getSession().removeAttribute(emailOrPhone);
        message = JsonBody.success();

        return SUCCESS;
    }

    // 传入要验证的字段，以及需要的正则表达式， 进行字段的验证
    private boolean ExecRegex(String args, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(args);
        System.out.println(matcher.matches());
        return matcher.matches();
    }

    @Action("isExist")
    public String emailOrPhoneIsExist() {

        if (emailOrPhone == null) {
            message = JsonBody.fail();
            message.setMessage("数据为空");
            return SUCCESS;
        }

        //如果验证是否为邮箱或者密码
        if (!RegexString.ExecRegex(emailOrPhone, RegexString.regex_UserEmail) &&
                !RegexString.ExecRegex(emailOrPhone, RegexString.regex_UserPhone)) {
            message = JsonBody.fail();
            message.setMessage("前端数据出错");
            return SUCCESS;
        }

        int flag = 0;

        if (ExecRegex(emailOrPhone, RegexString.regex_UserEmail)) {
            flag = userService.isExistEmail(emailOrPhone);
        } else {
            flag = userService.isExistPhone(emailOrPhone);
        }

        if (flag == 1) {
            message = JsonBody.fail();
            message.setMessage("账号已经存在");
            return SUCCESS;
        } else {
            message = JsonBody.success();
            message.setMessage("账号可以使用");
            return SUCCESS;
        }


    }

}
