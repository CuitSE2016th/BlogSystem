package com.bs.ssh.action.user.article;

import com.bs.ssh.bean.JsonBody;
import com.bs.ssh.service.user.UserFileService;
import com.bs.ssh.utils.Constants;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Objects;

import com.bs.ssh.utils.JsonUtil;

/**
 * 图片上传动作
 *
 * @author Egan
 * @date 2018/12/17 14:40
 **/
@ParentPackage("json-default")
public class UploadPictureAction extends AbstractArticleAction {

    private File[] image;
    private String[] imageFileName;
    private String[] imageContentType;

    @Autowired
    private UserFileService fileService;

    @Action("/user/article/upload")
    @InputConfig(methodName = "verify")
    @Override
    public String execute() throws Exception {

        if(Constants.FILE_IMAGE_RELATIVE_PATH == null){
            Constants.FILE_IMAGE_RELATIVE_PATH =
                    ServletActionContext.getServletContext().getRealPath(Constants.IMAGE_PATH);
        }

        try {
            String uid = this.getUserId();
//            String uid = "00000";
            Object[] path = fileService.uploadPicture(uid, image, imageFileName);
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
    public File[] getImage() {
        return image;
    }

    public void setImage(File[] image) {
        this.image = image;
    }

    public String[] getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String[] imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String[] getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String[] imageContentType) {
        this.imageContentType = imageContentType;
    }
}
