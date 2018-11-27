package com.bs.ssh.action;

import com.bs.ssh.bean.JsonBody;
import com.bs.ssh.utils.Constants;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 通用动作
 *
 * @author Egan
 * @date 2018/11/12 12:44
 **/
public class BaseAction extends ActionSupport {


    protected JsonBody<Object> result = new JsonBody<Object>();

    protected final String JSON = "json";

//    protected PageRequest pageRequest = new PageRequest();
//
//    protected int pn;
//    protected int ps;

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

//    @DateRangeFieldValidator(message = "页号不合法", min = "0")
//    public int getPn() {
//        return pn;
//    }
//
//    public void setPn(int pn) {
//        pageRequest.setPageNumber(pn);
//        this.pn = pn;
//    }
//
//    @DateRangeFieldValidator(message = "单页记录数不合法", min = "1", max = "50")
//    public int getPs() {
//        return ps;
//    }
//
//    public void setPs(int ps) {
//        pageRequest.setPageSize(ps);
//        this.ps = ps;
//    }
}
