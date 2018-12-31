package com.bs.ssh.action.user;

import com.bs.ssh.action.user.article.AbstractArticleAction;
import com.bs.ssh.bean.JsonBody;
import com.bs.ssh.service.user.UserFileService;
import com.bs.ssh.utils.Constants;
import com.bs.ssh.utils.JsonUtil;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Objects;

/**
 * 头像上传动作
 *
 * @author Egan
 * @date 2018/12/17 14:40
 **/
@ParentPackage("json-default")
public class UploadAvatarAction extends AbstractArticleAction {

    private File pic;
    private String picFileName;
    private String picContentType;

    @Autowired
    private UserFileService fileService;

    @Action("/user/uploadPortrait")
    @InputConfig(methodName = "verify")
    @Override
    public String execute() throws Exception {

        if(Constants.FILE_IMAGE_RELATIVE_PATH == null){
            Constants.FILE_IMAGE_RELATIVE_PATH =
                    ServletActionContext.getServletContext().getRealPath(Constants.IMAGE_PATH);
        }

        try {
            String uid = this.getUserId();
//            String uid = "59350743452";
            String path = fileService.uploadAvatar(uid, pic, picFileName);
            result = JsonBody.success();
            result.setData(path);
            ServletActionContext.getResponse().setCharacterEncoding("utf-8");
            ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
            ServletActionContext.getResponse().getWriter().write(Objects.requireNonNull(JsonUtil.toJson(result)));
            ServletActionContext.getResponse().getWriter().flush();
            return null;

        }catch (Exception e){
            result = JsonBody.fail();
            result.setMessage(e.getMessage());
            return JSON;
        }

    }

    @RequiredFieldValidator(message = "请先选择上传文件")
    public File getPic() {
        return pic;
    }

    public void setPic(File pic) {
        this.pic = pic;
    }

    public String getPicFileName() {
        return picFileName;
    }

    public void setPicFileName(String picFileName) {
        this.picFileName = picFileName;
    }

    public String getPicContentType() {
        return picContentType;
    }

    public void setPicContentType(String picContentType) {
        this.picContentType = picContentType;
    }
}
