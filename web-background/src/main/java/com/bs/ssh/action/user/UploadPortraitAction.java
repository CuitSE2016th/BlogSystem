//
//
//package com.bs.ssh.action.user;
//
//import com.bs.ssh.action.BaseAction;
//import com.bs.ssh.service.user.UserService;
//import com.bs.ssh.utils.Constants;
//
//import org.apache.struts2.ServletActionContext;
//import org.apache.struts2.convention.annotation.Action;
//import org.apache.struts2.convention.annotation.Namespace;
//import org.apache.struts2.convention.annotation.ParentPackage;
//import org.apache.struts2.convention.annotation.Result;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.File;
//
//@Namespace("/user")
//@ParentPackage("default")
//public class UploadPortraitAction extends BaseAction {
//
//
//    @Override
//    @Action(
//            value = "uploadPortrait",
//            results = {@Result(
//                    type = "chain",
//                    name = "success",
//                    location = "updateUserInfo"
//            )}
//    )
//    @NotNull
//    public String execute() throws Exception {
//        String var1 = "打印测试 , " + this.picContentType + " , " + this.picFileName + " , " + this.getSavePath();
//        System.out.println(var1);
//        this.headPortrait = this.userService != null ? this.userService.updateUserPortrait(this.pic, this.getSavePath(), this.picFileName, this.identity) : null;
//        return super.execute();
//    }
//
//
//    @Nullable
//    private String identity;
//    @Nullable
//    private String token;
//    @Nullable
//    private String headPortrait;
//    @Nullable
//    private File pic;
//    @Nullable
//    private String picContentType;
//    @Nullable
//    private String picFileName;
//    private String savePath;
//    @Autowired
//    private UserService userService;
//
//    @Nullable
//    public final String getIdentity() {
//        return this.identity;
//    }
//
//    public final void setIdentity(@Nullable String var1) {
//        this.identity = var1;
//    }
//
//    @Nullable
//    public final String getToken() {
//        return this.token;
//    }
//
//    public final void setToken(@Nullable String var1) {
//        this.token = var1;
//    }
//
//    @Nullable
//    public final String getHeadPortrait() {
//        return this.headPortrait;
//    }
//
//    public final void setHeadPortrait(@Nullable String var1) {
//        this.headPortrait = var1;
//    }
//
//    @Nullable
//    public final File getPic() {
//        return this.pic;
//    }
//
//    public final void setPic(@Nullable File var1) {
//        this.pic = var1;
//    }
//
//    @Nullable
//    public final String getPicContentType() {
//        return this.picContentType;
//    }
//
//    public final void setPicContentType(@Nullable String var1) {
//        this.picContentType = var1;
//    }
//
//    @Nullable
//    public final String getPicFileName() {
//        return this.picFileName;
//    }
//
//    public final void setPicFileName(@Nullable String var1) {
//        this.picFileName = var1;
//    }
//
//    private final String getSavePath() {
//        return ServletActionContext.getServletContext().getRealPath(Constants.FILE_IMAGE_RELATIVE_PATH);
//    }
//}
