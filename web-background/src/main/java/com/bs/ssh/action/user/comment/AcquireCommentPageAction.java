package com.bs.ssh.action.user.comment;

import com.bs.ssh.bean.JsonBody;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

public class AcquireCommentPageAction extends AbstractCommentPageAction {

    private Integer aid;
    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    @Action("/user/comment/page")
    @Validations(
            intRangeFields = {
                    @IntRangeFieldValidator(fieldName = "pn", message = "页号不合法", min = "1"),
                    @IntRangeFieldValidator(fieldName = "ps", message = "单页记录数不合法", min = "1", max = "50")
            },
            requiredFields = {
                    @RequiredFieldValidator(fieldName = "aid", message = "文章ID不能为空")
            }
    )
    @InputConfig(methodName = "verify")
    @Override
    public String execute() throws Exception {
        try {
            commentService.findAllComment(pageRequest, aid);
        }catch (Exception e){
            result = JsonBody.fail();
            result.setData(e.getMessage());
        }finally {
            return JSON;
        }
    }
}
