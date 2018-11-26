package com.bs.ssh.action.admin;

import com.bs.ssh.action.BaseAction;
import com.bs.ssh.entity.Article;
import com.bs.ssh.bean.JsonBody;
import com.bs.ssh.bean.PageBean;
import com.bs.ssh.service.admin.impl.ArticleAdminServiceImpl;
import com.bs.ssh.utils.Constants;
import com.bs.ssh.utils.RegexString;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Create By ZZY on 2018/11/23
 */
@ParentPackage("json-default")
@Namespace("/articleadmin")
@Results({
        @Result(name = "success", type = "json", params = {"root", "result"})
})
public class ArticleAdminAction extends BaseAction {

    @Autowired
    private ArticleAdminServiceImpl articleAdminService;

    //前端来的查询文章的ID
    private String articleID;

    //前端传入的状态ID
    private String status;


    private String pageNo;

    private static final int pageSize = 15;

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Action("getArticleByArticleID")
    public String getArticleByArticleID(){

        if(!validationData(articleID)){
            result = JsonBody.fail();
            result.setMessage("前端数据出错！");
            return SUCCESS;
        }

        String article = articleAdminService.getArticleByArticleID(articleID);

        result = JsonBody.success();
        result.setData(article);
        return SUCCESS;
    }

    @Action("setArticleStatus")
    public String setArticleStatus(){

        if(!validationData(status) || !validationData(articleID)){
            result = JsonBody.fail();
            result.setMessage("前端数据出错！");
            return SUCCESS;
        }

        int articleStatus = Integer.parseInt(status);

        if(articleStatus != Constants.AUDIT_IN && articleStatus != Constants.AUDIT_IN_COMPUTER &&
                articleStatus != Constants.AUDIT_IN_ADMIN && articleStatus != Constants.AUDIT_COMPLETE){
            result = JsonBody.fail();
            result.setMessage("状态码数据出错！");
            return SUCCESS;
        }

        int flag = articleAdminService.setArticleStatus(articleID,articleStatus);

        result = flag == 0?JsonBody.fail():JsonBody.success();

        return SUCCESS;

    }

    @Action("getArticlesForPage")
    public String getArticlesForPage(){
        if(!validationData(pageNo)){
            result = JsonBody.fail();
            result.setMessage("前端数据出错！");
            return SUCCESS;
        }

        int pn = Integer.parseInt(pageNo);
        if(pn < 0){
            pn = 1;
        }

        PageBean page = articleAdminService.getArticlesForPage(pn, pageSize);

        return SUCCESS;
    }

    /**
     * 验证数据格式
     * @param args 用户传入的数据
     * @return
     */
    private boolean validationData(String args){
        if(args == null || args.trim().equals("")
                || !RegexString.ExecRegex(args, RegexString.regex_DIGITAL)){
            return false;
        }
        return true;
    }

}
