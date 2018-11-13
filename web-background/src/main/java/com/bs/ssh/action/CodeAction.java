package com.bs.ssh.action;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.bs.ssh.beans.JsonBody;
import com.bs.ssh.common.alibaba.AliSmsUtil;
import com.bs.ssh.common.email163.EmailCodeMap;
import com.bs.ssh.common.email163.MailUtil;
import com.bs.ssh.utils.RegexString;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

/**
 * Create By ZZY on 2018/11/10
 */
@ParentPackage("json-default")
@Results({
        @Result(name = "success", type = "json", params = {"root", "message"})
})
public class CodeAction extends ActionSupport {

    private String emailOrPhone;

    private JsonBody message = null;

    public JsonBody getMessage() {
        return message;
    }

    public void setMessage(JsonBody message) {
        this.message = message;
    }

    public String getEmailOrPhone() {
        return emailOrPhone;
    }

    public void setEmailOrPhone(String emailOrPhone) {
        this.emailOrPhone = emailOrPhone;
    }

    @Action("code")
    public String sendEmailCode() {

        if(!RegexString.ExecRegex(emailOrPhone, RegexString.regex_UserEmail) &&
                !RegexString.ExecRegex(emailOrPhone, RegexString.regex_UserPhone)){
            message = JsonBody.fail();
            return SUCCESS;
        }

        //得到即将发送的随机注册码
        String emailOrPhoneCode = MailUtil.EmailCode(6);

        //如果是邮箱则发送邮箱验证码
        if (RegexString.ExecRegex(emailOrPhone, RegexString.regex_UserEmail)) {

            int flag = MailUtil.codeMail(emailOrPhone, emailOrPhoneCode);

            if (flag == 0) {
                message = JsonBody.fail();
                return SUCCESS;
            }

            //如果是手机号则发送手机号验证码
        }else if(RegexString.ExecRegex(emailOrPhone, RegexString.regex_UserPhone)){

            SendSmsResponse response = null;

            try {
                response = AliSmsUtil.sendSms(emailOrPhone, emailOrPhoneCode);
            }catch (ClientException e){
                System.out.println("发送手机号码出错");
                message = JsonBody.fail();
                return SUCCESS;
            }

        }else{
            message = JsonBody.fail();
            return SUCCESS;
        }

        ServletActionContext.getRequest().getSession().setAttribute(emailOrPhone, emailOrPhoneCode);

        message = JsonBody.success();
        return SUCCESS;
    }
}