package com.bs.ssh.action.user.article;

import com.bs.ssh.bean.JsonBody;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

@Namespace("/user/article")
public class AcquireAuthorArticlePageAction extends AbstractArticlePageAction {


    @Action("/myself")
    @Validations(
            intRangeFields = {
                    @IntRangeFieldValidator(fieldName = "pn", message = "页号不合法", min = "1"),
                    @IntRangeFieldValidator(fieldName = "ps", message = "单页记录数不合法", min = "1", max = "50")
            }
    )
    @InputConfig(methodName = "verify")
    @Override
    public String execute() {
        try{
            result= JsonBody.success();
            result.setData(articleService.getAuthorArticle(pageRequest, getUserId()));
        }catch (Exception e){
            result=JsonBody.fail();
            result.setData(e.getMessage());
        }
        return JSON;
    }
}
