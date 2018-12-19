package com.bs.ssh.action.user.comment;

import com.bs.ssh.bean.JsonBody;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import org.apache.struts2.convention.annotation.Action;

public class ReplyCommentAction extends AbstractCommentAction{

    private Integer pid;
    private String content;


    @Action("/user/comment/reply")
    @Validations(
            requiredFields = {
                    @RequiredFieldValidator(fieldName = "pid", message = "父评论ID不能为空")
            },
            requiredStrings = {
                    @RequiredStringValidator(fieldName = "content", message = "内容不能为空")
            },
            stringLengthFields = {
                    @StringLengthFieldValidator(fieldName = "content",
                            minLength = "1", maxLength = "400",
                            message = "内容长度不合法"),
            }
    )
    @InputConfig(methodName = "verify")
    @Override
    public String execute() throws Exception {
        try {
            commentService.reply(this.getUserId(), pid, content);
        }catch (Exception e){
            result = JsonBody.fail();
            result.setData(e.getMessage());
        }
        return JSON;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
