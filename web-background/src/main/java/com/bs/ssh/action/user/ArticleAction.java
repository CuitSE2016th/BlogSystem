package com.bs.ssh.action.user;

import com.bs.ssh.action.BaseAction;
import com.bs.ssh.bean.JsonBody;
import com.bs.ssh.bean.PageRequest;
import com.bs.ssh.service.user.UserArticleService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.*;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@ParentPackage("json-default")
@Namespace("/user/article")
@Results({
        @Result(name = "json", type = "json", params = {"root", "result"})
})
public class ArticleAction extends BaseAction {

    @Autowired
    UserArticleService articleService;

    @Action("/all")
    @Validations(
            requiredFields = {
                    @RequiredFieldValidator(fieldName = "pn", message = "页号不能为空", type = ValidatorType.SIMPLE),
                    @RequiredFieldValidator(fieldName = "ps", message = "单页总参数不能为空", type = ValidatorType.SIMPLE)
            })
    @InputConfig(methodName = "verify")
    @Override
    public String execute() {
        result= JsonBody.success();
        result.setData(articleService.getAllArticle(pageRequest));
        return JSON;
    }

    private PageRequest pageRequest = new PageRequest();

    private int pn;
    private int ps;

    @IntRangeFieldValidator(message = "页号不合法", min = "1")
    public int getPn() {
        return pn;
    }

    public void setPn(int pn) {
        pageRequest.setPageNumber(pn);
        this.pn = pn;
    }

    @IntRangeFieldValidator(message = "单页记录数不合法", min = "1", max = "50")
    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        pageRequest.setPageSize(ps);
        this.ps = ps;
    }

}
