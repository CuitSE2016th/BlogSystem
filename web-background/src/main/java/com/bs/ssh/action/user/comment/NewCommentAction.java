package com.bs.ssh.action.user.comment;

import com.bs.ssh.bean.JsonBody;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

public class NewCommentAction extends AbstractCommentAction{

    private String content;

    @Action("/user/comment/new")
    @Validations(
            requiredStrings = {
                    @RequiredStringValidator(fieldName = "aid", message = "文章ID不能为空"),
                    @RequiredStringValidator(fieldName = "content", message = "内容不能为空")
            },
            stringLengthFields = {
                    @StringLengthFieldValidator(fieldName = "content",
                            minLength = "1", maxLength = "400",
                            message = "内容长度不合法"),
            }
    )
    @Override
    public String execute() throws Exception {
        try {
            commentService.newComment(this.getUserId(), aid, content);
            result = JsonBody.success();
            return JSON;
        }catch (Exception e){
            result = JsonBody.fail();
            result.setData(e.getMessage());
        }
        return JSON;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
