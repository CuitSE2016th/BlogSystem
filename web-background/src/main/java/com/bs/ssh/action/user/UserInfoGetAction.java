package com.bs.ssh.action.user;

import com.bs.ssh.action.BaseAction;
import com.bs.ssh.beans.JsonBody;
import com.bs.ssh.beans.User;
import com.bs.ssh.utils.JsonUtil;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import java.util.ArrayList;

/**
 * Created by 98620 on 2018/11/12.
 */
@ParentPackage("default")
class UserInfoGetAction extends BaseAction {

    @Action("getUserInfo")
    @Override
    public String execute() throws Exception {
        ArrayList<User> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId("00000" + i);
            user.setEmail("lfork" + i + "@qq.com");
            user.setNickname("enen??" + i);
            user.setPhone("18628904" + (100 + i));
            user.setSex("男");
            data.add(user);
        }
        //返回给客户端的结果
        JsonBody<ArrayList<User>> result = new JsonBody<>();
        result.setData(data);
        result.setCode(100);
        result.setMessage("Success");

        JsonUtil.returnJson(result);

        return super.execute();

    }
}
