package com.bs.ssh.action;

import com.bs.ssh.bean.IndexArticle;
import com.bs.ssh.bean.JsonBody;
import com.bs.ssh.bean.PageBean;
import com.bs.ssh.service.user.UserService;
import com.bs.ssh.service.user.impl.UserServiceImpl;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Create By ZZY on 2018/12/30
 */
@ParentPackage("json-default")
@Namespace("/user")
@Results({
        @Result(name = "success", type = "json", params = {"root", "result"})
})
public class IndexAction extends BaseAction {

    @Autowired
    private UserServiceImpl userService;

    //需要查询的页数
    private String pageNo;

    //分页的数量
    private String pageNum;

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    @Action("getArticlePage")
    public String getArticlePage(){

        int pn;

        int pNum;

        try {
            pn = Integer.parseInt(pageNo);
        }catch (NumberFormatException e){
            result = JsonBody.fail();
            result.setMessage("传入页数非数字");
            return SUCCESS;
        }

        if(pn <= 0 ){
            pn =1;
        }

        if(pageNum == null){
            pNum = 8;
        }else{
            try {
                pNum = Integer.parseInt(pageNum);
            }catch (NumberFormatException e){
                result = JsonBody.fail();
                result.setMessage("传入分页数量非数字");
                return SUCCESS;
            }
        }


        if(pNum <=  0){
            pNum = 8;
        }

        PageBean<List> pageBean = userService.getArticlePage(pn, pNum);

        if(pageBean == null){
            result = JsonBody.fail();
            result.setMessage("数据查询失败");
            return SUCCESS;
        }
        result = JsonBody.success();
        result.setData(pageBean);
        return SUCCESS;
    }


}
