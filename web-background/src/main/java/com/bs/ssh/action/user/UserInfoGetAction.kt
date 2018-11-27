package com.bs.ssh.action.user

import com.bs.ssh.action.BaseAction
import com.bs.ssh.service.user.UserService
import com.bs.ssh.utils.JsonUtil

import org.apache.struts2.convention.annotation.Action
import org.apache.struts2.convention.annotation.ParentPackage
import org.springframework.beans.factory.annotation.Autowired

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
        JsonUtil.returnJson(result)
        return super.execute()
    }
}
