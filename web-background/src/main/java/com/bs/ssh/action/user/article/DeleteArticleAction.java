package com.bs.ssh.action.user.article;

import com.bs.ssh.action.BaseAction;
import com.bs.ssh.bean.JsonBody;
import com.bs.ssh.service.user.UserArticleService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

public class DeleteArticleAction extends BaseAction {

    /**
     * 文章ID
     **/
    private Integer aid;

    @Autowired
    UserArticleService articleService;

    @Action("/delete")
    @Validations(
            requiredFields = {
                    @RequiredFieldValidator(fieldName = "aid", message = "文章ID不能为空")
            }
    )
    @InputConfig(methodName = "verify")
    @Override
    public String execute(){
        try{
            String uid = getUserId();
            articleService.deleteArticle(uid, aid);
            result = JsonBody.success();
        }catch (Exception e){
            result = JsonBody.fail();
            result.setMessage(e.getMessage());
        }

        return JSON;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }
}
