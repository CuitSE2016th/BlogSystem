package com.bs.ssh.action.user;

import com.bs.ssh.action.BaseAction;
import com.bs.ssh.bean.JsonBody;
import com.bs.ssh.service.user.UserService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 关注用户
 *
 * @author Egan
 * @date 2019/1/1 16:33
 **/
public class FollowAction extends BaseAction {

    @Autowired
    private UserService userService;

    private String followerid;

    @RequiredStringValidator(message = "被关注者id不能为空")
    public String getFollowerid() {
        return followerid;
    }

    public void setFollowerid(String followerid) {
        this.followerid = followerid;
    }

    @Action("/user/addfollow")
    @InputConfig(methodName = "verify")
    @Override
    public String execute() throws Exception {

        try {
            userService.followUser(followerid, this.getUserId());
            result = JsonBody.success();
            return JSON;
        }catch (Exception e){
            result = JsonBody.fail();
            result.setData(e.getMessage());
            return JSON;
        }
    }
}
