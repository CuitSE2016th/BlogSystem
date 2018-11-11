package com.bs.ssh.action;

import com.bs.ssh.beans.Msg;
import com.bs.ssh.common.email163.EmailCodeMap;
import com.bs.ssh.common.email163.MailUtil;
import com.bs.ssh.utils.RegexString;

/**
 * Create By ZZY on 2018/11/10
 */
public class MailCodeAction {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Msg sendEmailCode() {

        if (RegexString.ExecRegex(email, RegexString.regex_UserEmail)) {
            return Msg.fail().add("error", "邮箱格式不正确");
        }

        String emailCode = MailUtil.EmailCode(6);

        int flag = MailUtil.codeMail(email, emailCode);

        if (flag == 0) {
            return Msg.fail().add("error", "验证码发送失败");
        }

        //将发送记录发送至
        EmailCodeMap.emailCodeMap.put(email, emailCode);

        return Msg.success();


    }

}
