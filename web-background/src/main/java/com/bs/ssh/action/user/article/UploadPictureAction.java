package com.bs.ssh.action.user.article;

import com.bs.ssh.bean.JsonBody;
import com.bs.ssh.service.user.UserFileService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

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



        try {
            String uid = this.getUserId();
            String[] path = fileService.uploadPicture(uid, image, imageFileName);
            result = JsonBody.success();
            result.setData(path);
        }catch (Exception e){
            result = JsonBody.fail();
            result.setMessage(e.getMessage());
        }

        return JSON;
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
