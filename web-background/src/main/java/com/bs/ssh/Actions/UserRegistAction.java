package com.bs.ssh.Actions;

import com.bs.ssh.beans.Msg;
import com.bs.ssh.common.email163.MailUtil;
import com.bs.ssh.utils.RegexString;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create By ZZY on 2018/11/9
 */
public class UserRegistAction extends ActionSupport {

    private Msg message = null;
    private String email;
    private String password;
    private String emailCode;

    public Msg getMessage() {
        return message;
    }

    public void setMessage(Msg message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }

    public String userRegist() {

        /**
         * 验证邮箱数据是否合法
         */
        if (!ExecRegex(email, RegexString.regex_UserEmail)) {
            message = Msg.fail();
            return SUCCESS;
        }

        /**
         * 验证密码是否合法
         */
        if (!ExecRegex(password, RegexString.regex_UserPassword)) {
            message = Msg.fail();
            return SUCCESS;
        }

        //在后台得到我们发送给前端的数据
        String emailCode_after = (String) ServletActionContext.getRequest().getSession().getAttribute(email);
        if (emailCode_after == null || "".equals(emailCode_after)){
            message = Msg.fail();
            return SUCCESS;
        }

        //比较前后端验证码是否相同
        if(!emailCode_after.equals(emailCode)){
            message = Msg.fail();
            return SUCCESS;
        }






        message = Msg.success();

        return SUCCESS;
    }

    // 传入要验证的字段，以及需要的正则表达式， 进行字段的验证
    private boolean ExecRegex(String args, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(args);
        System.out.println(matcher.matches());
        return matcher.matches();
    }


}
