package com.bs.ssh.action.user.comment;

import com.bs.ssh.bean.JsonBody;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import org.apache.struts2.convention.annotation.Action;

public class DeleteCommentAction extends AbstractCommentAction {

    private int cid;

    @Action("/user/comment/delete")
    @Validations(
            requiredStrings = {
                    @RequiredStringValidator(fieldName = "cid", message = "评论ID不能不为空")
            }
    )
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

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
