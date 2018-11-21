package com.bs.ssh.action.root;

import com.bs.ssh.action.BaseAction;
import com.bs.ssh.beans.JsonBody;
import com.bs.ssh.beans.PageBean;
import com.bs.ssh.beans.User;
import com.bs.ssh.service.impl.UserServiceImpl;
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
@Namespace("/root")
@Results({
        @Result(name = "success", type = "json", params = {"root", "result"})
})
public class RootAction extends BaseAction {

    @Autowired
    private UserServiceImpl userService;

    //需要查询的页数
    private String pageNo;

    //暂时指定每一页的数据数量
    private static final int pageSize = 15;


    public String getAllUserToPage(){

        int pn;

        try {
            pn = Integer.parseInt(pageNo);
        }catch (Exception e){
            result = JsonBody.fail();
            result.setMessage("传入页数非数字");
            return SUCCESS;
        }

        if (pn <= 0){
            pn = 1;
        }

        PageBean<User> users = userService.getAllUserToPageBean(pn, pageSize);




        return null;
    }




}
