package com.bs.ssh.action.user

import com.bs.ssh.action.BaseAction
import com.bs.ssh.entity.User
import com.bs.ssh.service.user.UserService
import com.bs.ssh.utils.JsonUtil

import org.apache.struts2.convention.annotation.Action
import org.apache.struts2.convention.annotation.Namespace
import org.apache.struts2.convention.annotation.ParentPackage
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by 98620 on 2018/11/12.
 */
@Namespace("/user")
@ParentPackage("default")
class UserInfoUpdateAction : BaseAction() {

    var identity: String? = null

    var token: String? = null

    var nickname: String? = null

    var sex: String? = null

    var headPortrait: String? = null


    @Autowired
    private val userService: UserService? = null

    @Action("updateUserInfo")
    @Throws(Exception::class)
    override fun execute(): String {
        val tempUser = User(nickname, headPortrait, sex)
        val result = userService?.updateUserInfo(tempUser, identity, token)
        JsonUtil.returnJson(result)
        return super.execute()
    }
}
