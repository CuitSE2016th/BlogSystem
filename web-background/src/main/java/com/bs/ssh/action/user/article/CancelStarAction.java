package com.bs.ssh.action.user.article;

import com.bs.ssh.bean.JsonBody;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

@Namespace("/user/article")
public class CancelStarAction extends AbstractArticleAction {

    private Integer aid;
    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    @Action("/cancelstar")
    @Validations(
            requiredFields = {@RequiredFieldValidator(fieldName = "aid", message = "文章ID不能为空")}
    )
    @InputConfig(methodName = "verify")
    @Override
    public String execute() throws Exception {
        try {
            String uid = this.getUserId();
            articleService.cancelStar(uid, aid);
            result = JsonBody.success();
        }catch (Exception e){
            result = JsonBody.fail();
            result.setData(e.getMessage());
        }finally {
            return JSON;
        }
    }

}
