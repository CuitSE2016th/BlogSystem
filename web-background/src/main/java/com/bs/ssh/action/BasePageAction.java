package com.bs.ssh.action;

import com.bs.ssh.bean.PageRequest;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;

/**
 * 基本分页动作
 * 继承自BaseAction，并添加分页相关参数
 * 适用于需要分页请求的动作
 *
 * @author Egan
 * @date 2018/11/29 15:19
 **/
public class BasePageAction extends BaseAction{
    protected int pn;
    protected int ps;

    protected PageRequest pageRequest = new PageRequest();

    //        @IntRangeFieldValidator(message = "页号不合法", min = "1")
    public int getPn() {
        return pn;
    }

    public void setPn(int pn) {
        pageRequest.setPageNumber(pn);
        this.pn = pn;
    }

    //    @IntRangeFieldValidator(message = "单页记录数不合法", min = "1", max = "50")
    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        pageRequest.setPageSize(ps);
        this.ps = ps;
    }
}
