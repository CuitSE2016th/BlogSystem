package com.bs.ssh.action.admin;

import com.bs.ssh.action.BaseAction;
import com.bs.ssh.bean.JsonBody;
import com.bs.ssh.bean.PageBean;
import com.bs.ssh.entity.User;
import com.bs.ssh.service.admin.impl.UserAdminServiceImpl;
import com.bs.ssh.utils.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Create By ZZY on 2018/11/21
 */
@ParentPackage("json-default")
@Namespace("/useradmin")
@Results({
        @Result(name = "success", type = "json", params = {"root", "result"})
})
public class UserAdminAction extends BaseAction {

    private String loginId;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Autowired
    private UserAdminServiceImpl userAdminService;

    //需要查询的页数
    private String pageNo;

    //暂时指定每一页的数据数量
    private static final int pageSize = 15;

    //前端传入的数据信息{邮箱或者电话}
    private String identity;

    //前端传入的用户ID
    private String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Action("getUsersToPageByPageNo")
    public String getUsersToPageByPageNo(){

        if(validationUserRole() == false){
            result = JsonBody.fail();
            result.setMessage("角色出错！");
            return SUCCESS;
        }

        int pn;

        try {
            pn = Integer.parseInt(pageNo);
        }catch (NumberFormatException e){
            result = JsonBody.fail();
            result.setMessage("传入页数非数字");
            return SUCCESS;
        }

        if (pn <= 0){
            pn = 1;
        }

        PageBean users = userAdminService.getAllUserToPageBean(pn, pageSize);

        if(users == null){
            result = JsonBody.fail();
            result.setMessage("数据查询失败");
            return SUCCESS;
        }

        result = JsonBody.success();
        result.setData(users);
        return SUCCESS;
    }

    private boolean validationUserRole() {

        Subject loginuser = SecurityUtils.getSubject();
        User user = (User) loginuser.getPrincipal();
        if(user.getRoleId() != Constants.USERADMIN_ROLR_ID){
            return false;
        }else{
            return true;
        }

    }

    @Action("deleteUserByUserID")
    public String deleteUserByUserID(){

        if (userID == null){
            result = JsonBody.fail();
            result.setMessage("前端消息出错！");
            return SUCCESS;
        }

        Subject loginUser = SecurityUtils.getSubject();
        User  user = (User) loginUser.getPrincipal();

        if(user == null){
            result = JsonBody.fail();
            result.setMessage("用户没有登录！");
            return SUCCESS;
        }

        if(!loginId.equals(user.getId())){
            result = JsonBody.fail();
            result.setMessage("用户不一致,非法操作！");
            return SUCCESS;
        }



        int flag = userAdminService.deleteUserByUserID(userID);

        if(flag == 0){
            result = JsonBody.fail();
            result.setMessage("删除失败！");

            return SUCCESS;
        }

        result = JsonBody.success();

        return SUCCESS;
    }

    @Action("getUserByUserID")
    public String getUserByUserID(){

        if(validationUserRole() == false){
            result = JsonBody.fail();
            result.setMessage("角色出错！");
            return SUCCESS;
        }

        if(identity == null){
            result = JsonBody.fail();
            result.setMessage("前端数据出错！");
            return SUCCESS;
        }

        User user = userAdminService.getUserByUserID(identity);

        if(user == null){
            result = JsonBody.fail();
            result.setMessage("数据查询出错！");
            return SUCCESS;
        }

        result = JsonBody.success();
        result.setData(user);
        return SUCCESS;

    }













}
