package com.bs.ssh.action;

import com.bs.ssh.bean.JsonBody;
import com.bs.ssh.entity.User;
import com.bs.ssh.utils.Constants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import org.apache.shiro.SecurityUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

/**
 * 通用动作
 *
 * @author Egan
 * @date 2018/11/12 12:44
 **/
@ParentPackage("json-default")
@Results({
        @Result(name = "json", type = "json", params = {"root", "result"})
})
public class BaseAction extends ActionSupport {


    protected JsonBody<Object> result = new JsonBody<Object>();

    protected final String JSON = "json";

    public User getUser(){
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public String getUserId(){
        try {
            return this.getUser().getId();
        }catch (Exception ignore){
            return null;
        }
    }

    /**
     * 通用验证方法，减少工作量
     *
     * @date 2018/11/12 12:56
     * @param
     * @return java.lang.String
     **/
    public String verify(){
        result.setCode(Constants.RESPONSE_FAILED);
        result.setMessage(this.getFieldErrors().values().iterator().next().get(0));
        return JSON;
    }

    public JsonBody<Object> getResult() {
        return result;
    }

    public void setResult(JsonBody<Object> result) {
        this.result = result;
    }
}
