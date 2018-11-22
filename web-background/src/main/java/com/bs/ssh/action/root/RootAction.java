package com.bs.ssh.action.root;

import com.bs.ssh.action.BaseAction;
import com.bs.ssh.beans.JsonBody;
import com.bs.ssh.beans.PageBean;
import com.bs.ssh.beans.User;
import com.bs.ssh.service.impl.RootServiceImpl;
import com.bs.ssh.service.impl.UserServiceImpl;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 * Create By ZZY on 2018/11/21
 */
@ParentPackage("json-default")
@Namespace("/")
@Results({
        @Result(name = "success", type = "json", params = {"root", "result"})
})
public class RootAction extends BaseAction {

    @Autowired
    private RootServiceImpl rootService;

    //需要查询的页数
    private String pageNo;

    //前端发送的查询条件
    private String identity;

    //暂时指定每一页的数据数量
    private static final int pageSize = 15;

    //前段传到后台的用户类型
    private String type;

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    @Action("getUsersToPageByPageNo")
    public String getUsersToPageByPageNo(){

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

        PageBean users = rootService.getAllUserToPageBean(pn, pageSize);

        if(users == null){
            result = JsonBody.fail();
            result.setMessage("数据查询失败");
            return SUCCESS;
        }

        result = JsonBody.success();
        result.setData(users);
        return SUCCESS;
    }

    /*
    * 通过条件查询指定用户
    * */
    @Action("getUserByIdentity")
    public String getUserByIdentity(){
        if (identity == null){
            result = JsonBody.fail();
            result.setMessage("查询条件为空");
            return SUCCESS;
        }
        User user = rootService.getUserByIdentity(identity);
        if (user == null){
            result = JsonBody.fail();
            result.setMessage("查询结果为空");
            return SUCCESS;
        }
        result = JsonBody.success();
        result.setData(user);
        return SUCCESS;
    }

    public String updateUserTypeByUserPhone(){

        return null;
    }
}