package com.bs.ssh.action.user.article;

import com.bs.ssh.bean.JsonBody;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import org.apache.struts2.convention.annotation.*;

@Namespace("/user/article")
public class AcquireArticlePageAction extends AbstractArticlePageAction {


    @Action("/page")
    @Validations(
            intRangeFields = {
                    @IntRangeFieldValidator(fieldName = "pn", message = "页号不合法", min = "1"),
                    @IntRangeFieldValidator(fieldName = "ps", message = "单页记录数不合法", min = "1", max = "50")
            }
    )
    @InputConfig(methodName = "verify")
    @Override
    public String execute() {
        result= JsonBody.success();
        result.setData(articleService.getAllArticle(pageRequest));
        return JSON;
    }

}
