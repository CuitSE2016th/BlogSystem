package com.bs.ssh.action;

import com.bs.ssh.beans.JsonBody;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 通用动作
 *
 * @author Egan
 * @date 2018/11/12 12:44
 **/
public class BaseAction extends ActionSupport{

    protected JsonBody<String> result = new JsonBody<>();

    /**
     * 通用验证方法，减少工作量
     *
     * @date 2018/11/12 12:56
     * @param
     * @return java.lang.String
     **/
    public String verify(){
        result.setCode(405);
        result.setMessage(this.getFieldErrors().values().iterator().next().get(0));
        return "json";
    }

    public JsonBody<String> getResult() {
        return result;
    }

    public void setResult(JsonBody<String> result) {
        this.result = result;
    }

}
