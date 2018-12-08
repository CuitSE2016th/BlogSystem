package com.bs.ssh.action.user.article;

import com.bs.ssh.action.BaseAction;
import com.bs.ssh.bean.JsonBody;
import com.bs.ssh.service.user.UserArticleService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

public class PublishArticleAction extends BaseAction {

    @Autowired
    UserArticleService articleService;

    private String title;

    private String content;

    @Action("/publish")
    @Validations(
            requiredStrings = {
                    @RequiredStringValidator(fieldName = "title", message = "标题不能为空"),
                    @RequiredStringValidator(fieldName = "content", message = "内容不能为空")
            },
            stringLengthFields = {
                    @StringLengthFieldValidator(fieldName = "title",
                            minLength = "1", maxLength = "100",
                            message = "标题长度不合法"),
                    @StringLengthFieldValidator(fieldName = "content",
                            minLength = "1", maxLength = "10000",
                            message = "内容长度不合法"),
            }
    )
    @InputConfig(methodName = "verify")
    @Override
    public String execute(){
        try{
            String uid = getUserId();
            articleService.publishArticle(uid, title, content);
            result = JsonBody.success();
        }catch (Exception e){
            result = JsonBody.fail();
            result.setMessage(e.getMessage());
        }

        return JSON;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
