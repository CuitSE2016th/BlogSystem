package com.bs.ssh.action.user;

import com.bs.ssh.beans.JsonBody;
import com.bs.ssh.common.email163.EmailCodeMap;
import com.bs.ssh.common.email163.MailUtil;
import com.bs.ssh.utils.RegexString;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

/**
 * Create By ZZY on 2018/11/10
 */
public class MailCodeAction extends ActionSupport {

    private String email;

    private JsonBody message = null;

    public JsonBody getMessage() {
        return message;
    }

    public void setMessage(JsonBody message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String sendEmailCode() {

        if (RegexString.ExecRegex(email, RegexString.regex_UserEmail)) {
            message = JsonBody.fail();
        }

        String emailCode = MailUtil.EmailCode(6);

        int flag = MailUtil.codeMail(email, emailCode);

        if (flag == 0) {
            message = JsonBody.fail();
        }

        //将发送记录发送至
        EmailCodeMap.emailCodeMap.put(email, emailCode);

        ServletActionContext.getRequest().getSession().setAttribute(email, emailCode);

        message = JsonBody.success();

        return SUCCESS;
    }
}
