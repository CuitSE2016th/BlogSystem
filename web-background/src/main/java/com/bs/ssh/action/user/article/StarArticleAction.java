package com.bs.ssh.action.user.article;


import com.bs.ssh.bean.JsonBody;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import org.apache.struts2.convention.annotation.Namespace;

@Namespace("/user/article")
public class StarArticleAction extends AbstractArticleAction {


    @Validations(
            requiredStrings = {@RequiredStringValidator(fieldName = "aid", message = "文章ID不能为空")}
    )
    @InputConfig(methodName = "verify")
    @Override
    public String execute() throws Exception {
        try {
            String uid = this.getUserId();
            articleService.starArticle(uid, aid);
            result = JsonBody.success();
        }catch (Exception e){
            result = JsonBody.fail();
            result.setData(e.getMessage());
        }finally {
            return JSON;
        }
    }
}
