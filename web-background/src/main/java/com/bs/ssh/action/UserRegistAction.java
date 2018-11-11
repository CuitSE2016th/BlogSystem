package com.bs.ssh.action;

import com.bs.ssh.beans.Msg;
import com.bs.ssh.common.email163.MailUtil;
import com.bs.ssh.utils.RegexString;
import com.opensymphony.xwork2.ActionSupport;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create By ZZY on 2018/11/9
 */
public class UserRegistAction extends ActionSupport {

    private String email;
    private String password;
    private String emailCode;

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

    public Msg userRegist() {

        /**
         * 验证数据是否合法
         */
        if(!ExecRegex(email, RegexString.regex_UserEmail)){
          return Msg.fail().add("error", "邮箱格式不正确");
        }

        if(!ExecRegex(password, RegexString.regex_UserPassword)){
            return Msg.fail().add("error","密码格式不正确！");
        }

        //开始向指定邮箱发送电子邮件验证码
        String mailCode = MailUtil.EmailCode(6);
        int flag = MailUtil.codeMail(email, mailCode);

        //判断是否发送成功
        if(flag == 0){

        }

        return Msg.success();
    }

    // 传入要验证的字段，以及需要的正则表达式， 进行字段的验证
    private  boolean ExecRegex(String args, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(args);
        System.out.println(matcher.matches());
        return matcher.matches();
    }


}
