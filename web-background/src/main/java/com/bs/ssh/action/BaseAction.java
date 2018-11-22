package com.bs.ssh.action;

import com.bs.ssh.beans.JsonBody;
import com.bs.ssh.utils.Constants;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 通用动作
 *
 * @author Egan
 * @date 2018/11/12 12:44
 **/
public class BaseAction extends ActionSupport{

    protected JsonBody<Object> result = new JsonBody<>();

    protected final String JSON = "json";

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
