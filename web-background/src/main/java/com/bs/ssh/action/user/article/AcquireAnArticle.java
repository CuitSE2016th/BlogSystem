package com.bs.ssh.action.user.article;

import com.bs.ssh.bean.JsonBody;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

/**
 * 获取一篇文章
 *
 * @author Egan
 * @date 2018/12/29 22:59
 **/
@Namespace("/user/article")
public class AcquireAnArticle extends AbstractArticleAction{

    private Integer aid;
    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    @Action("/id")
    @Validations(
            requiredFields = {
                    @RequiredFieldValidator(fieldName = "aid", message = "文章ID不能为空")
            }
    )
    @InputConfig(methodName = "verify")
    @Override
    public String execute() {
        try {
            result= JsonBody.success();
            result.setData(articleService.getArticleById(aid, this.getUserId()));
        }catch (Exception e){
            result = JsonBody.fail();
            result.setData(e.getMessage());
        }

        return JSON;
    }
}
