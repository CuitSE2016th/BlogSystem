package com.bs.ssh.action.user.comment;

import com.bs.ssh.bean.JsonBody;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import org.apache.struts2.convention.annotation.Action;

public class DeleteCommentAction extends AbstractCommentAction {

    private Integer cid;
    private Integer aid;
    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    @Action("/user/comment/delete")
    @Validations(
            requiredFields = {
                    @RequiredFieldValidator(fieldName = "cid", message = "评论ID不能不为空")
            }
    )
    @InputConfig(methodName = "verify")
    @Override
    public String execute() throws Exception {
        try {
            commentService.deleteComment(this.getUserId(), cid);
            result = JsonBody.success();
            return JSON;
        }catch (Exception e){
            result = JsonBody.fail();
            result.setData(e.getMessage());
        }
        return JSON;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}
