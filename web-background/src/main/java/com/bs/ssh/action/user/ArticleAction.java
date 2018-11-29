package com.bs.ssh.action.user;

import com.bs.ssh.action.BaseAction;
import com.bs.ssh.action.BasePageAction;
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
public class ArticleAction extends BasePageAction {

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



}
