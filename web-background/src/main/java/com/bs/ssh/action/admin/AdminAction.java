package com.bs.ssh.action.admin;

import com.bs.ssh.action.BaseAction;
import com.bs.ssh.beans.JsonBody;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

/**
 * Create By ZZY on 2018/11/21
 */
@ParentPackage("json-default")
@Namespace("/admin")
@Results({
        @Result(name = "success", type = "json", params = {"root", "message"})
})
public class AdminAction extends BaseAction {


    private JsonBody message = null;

    public JsonBody getMessage() {
        return message;
    }

    public void setMessage(JsonBody message) {
        this.message = message;
    }
}
