package com.bs.ssh.action.user;

import com.bs.ssh.action.BaseAction;
import com.bs.ssh.bean.JsonBody;
import com.bs.ssh.bean.PageBean;
import com.bs.ssh.entity.Article;
import com.bs.ssh.entity.User;
import com.bs.ssh.service.user.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Create By ZZY on 2018/12/31
 */
@ParentPackage("json-default")
@Namespace("/user")
@Results({
        @Result(name = "success", type = "json", params = {"root", "result"})
})
public class UserAction extends BaseAction {

    private Integer pageNo;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    @Autowired
    private UserServiceImpl userService;

    @Action("getUserFollows")
    public String getUserFollows(){

        Subject user = SecurityUtils.getSubject();

        User userinfo = (User) user.getPrincipal();

        if(userinfo == null){
            result = JsonBody.fail();
            result.setData("用户信息获取失败！！！");
            return SUCCESS;
        }

        String userId = userinfo.getId();
        if(userId == null){
            result = JsonBody.fail();
            result.setData("用户ID获取失败！！！");
            return SUCCESS;
        }

        List<User> userFollows = userService.getUserFollows(userId);

        if(userFollows == null){
            result = JsonBody.fail();
            result.setData("用户关注者信息获取失败！！！");
            return SUCCESS;
        }

        result = JsonBody.success();
        result.setData(userFollows);
        return SUCCESS;
    }

    @Action("getUserFollowedUser")
    public String getUserFollowedUser(){
        Subject user = SecurityUtils.getSubject();

        User userinfo = (User) user.getPrincipal();

        if(userinfo == null){
            result = JsonBody.fail();
            result.setData("用户信息获取失败！！！");
            return SUCCESS;
        }

        String userId = userinfo.getId();
        if(userId == null){
            result = JsonBody.fail();
            result.setData("用户ID获取失败！！！");
            return SUCCESS;
        }

        List<User> userFollows = userService.getUserFollowedUser(userId);

        if(userFollows == null){
            result = JsonBody.fail();
            result.setData("用户关注者信息获取失败！！！");
            return SUCCESS;
        }

        result = JsonBody.success();
        result.setData(userFollows);
        return SUCCESS;
    }

    @Action("getUserLikeArticles")
    public String getUserLikeArticles(){
        Subject user = SecurityUtils.getSubject();

        User userinfo = (User) user.getPrincipal();

        if(userinfo == null){
            result = JsonBody.fail();
            result.setData("用户信息获取失败！！！");
            return SUCCESS;
        }

        String userId = userinfo.getId();
        if(userId == null){
            result = JsonBody.fail();
            result.setData("用户ID获取失败！！！");
            return SUCCESS;
        }

        if(pageNo <= 0){
            pageNo = 1;
        }

        PageBean<List> userFollows = userService.getUserLikeArticles(userId, pageNo);

        if(userFollows == null){
            result = JsonBody.fail();
            result.setData("用户关注者信息获取失败！！！");
            return SUCCESS;
        }

        result = JsonBody.success();
        result.setData(userFollows);
        return SUCCESS;
    }

}
