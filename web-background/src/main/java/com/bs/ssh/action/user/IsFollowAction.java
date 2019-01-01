package com.bs.ssh.action.user;

import com.bs.ssh.action.BaseAction;
import com.bs.ssh.bean.JsonBody;
import com.bs.ssh.service.user.UserService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class IsFollowAction extends BaseAction{

    @Autowired
    private UserService userService;

    private String followingid;
    private String followerid;

    @RequiredStringValidator(message = "关注者id不能为空")
    public String getFollowingid() {
        return followingid;
    }

    public void setFollowingid(String followingid) {
        this.followingid = followingid;
    }

    @RequiredStringValidator(message = "被关注者id不能为空")
    public String getFollowerid() {
        return followerid;
    }

    public void setFollowerid(String followerid) {
        this.followerid = followerid;
    }

    @Action("/user/isfollow")
    @InputConfig(methodName = "verify")
    @Override
    public String execute() throws Exception {
        try {
            result = JsonBody.success();
            result.setData(
                    userService.isFollow(followerid, followingid)
            );
        }catch (Exception e){
            result = JsonBody.fail();
            result.setData(e.getMessage());
        }
        return JSON;
    }
}
