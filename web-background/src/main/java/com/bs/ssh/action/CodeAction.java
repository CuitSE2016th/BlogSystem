package com.bs.ssh.action;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.bs.ssh.bean.JsonBody;
import com.bs.ssh.common.alibaba.AliSmsUtil;
import com.bs.ssh.common.email163.MailUtil;
import com.bs.ssh.service.user.impl.UserServiceImpl;
import com.bs.ssh.utils.RedisUtils;
import com.bs.ssh.utils.RegexString;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * Create By ZZY on 2018/11/10
 */
@ParentPackage("json-default")
@Namespace("/user")
@Results({
        @Result(name = "success", type = "json", params = {"root", "message"})
})
public class CodeAction extends ActionSupport {

    private static final Logger logger = LogManager.getLogger(CodeAction.class);

    private String emailOrPhone;

    private JsonBody message = null;

    @Autowired
    private UserServiceImpl userService;

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

        int v_flag = 0;

        if (RegexString.ExecRegex(emailOrPhone, RegexString.regex_UserEmail)) {
            v_flag = userService.isExistEmail(emailOrPhone);
        } else {
            v_flag = userService.isExistPhone(emailOrPhone);
        }

        if (v_flag == 1) {
            message = JsonBody.fail();
            message.setMessage("账号已经存在,请直接进行登录");
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

            //对于邮箱后缀进行处理
            String[] split = emailOrPhone.split("@");
            String email_suffer_upper = split[1].toLowerCase();
            emailOrPhone = split[0] + "@" + email_suffer_upper;

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

        try {
            if(RedisUtils.get(emailOrPhone) != null){
                RedisUtils.remove(emailOrPhone);
            }

            RedisUtils.set(emailOrPhone, emailOrPhoneCode);

            RedisUtils.expireKey(emailOrPhone, 15, TimeUnit.MINUTES);
        }catch (Exception e){
            message = JsonBody.fail();
            logger.error("存入redis出错");
            return SUCCESS;
        }

        message = JsonBody.success();
        return SUCCESS;
    }
}