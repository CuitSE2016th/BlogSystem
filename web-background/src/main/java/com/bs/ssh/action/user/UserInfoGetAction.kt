package com.bs.ssh.action.user

import com.bs.ssh.action.BaseAction
import com.bs.ssh.bean.JsonBody
import com.bs.ssh.entity.User
import com.bs.ssh.service.user.UserService
import com.bs.ssh.utils.JsonUtils

import org.apache.struts2.convention.annotation.Action
import org.apache.struts2.convention.annotation.ParentPackage
import org.springframework.beans.factory.annotation.Autowired

import java.util.ArrayList

/**
 * Created by 98620 on 2018/11/12.
 */
@ParentPackage("default")
internal class UserInfoGetAction : BaseAction() {

    var identity: String? = null

    var token: String? = null

    @Autowired
    private val userService: UserService? = null

    @Action("getUserInfo")
    @Throws(Exception::class)
    override fun execute(): String {
        val result = userService!!.getUserInfo(identity, token)
        JsonUtils.returnJson(result)
        return super.execute()
    }
}
