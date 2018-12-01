package com.lfork.blogsystem.data.user

import com.lfork.blogsystem.base.Config

data class User(var nickname: String?, var headPortrait: String?, var sex: String? = null) {
    constructor() : this(null, null, null)
    constructor(nickname: String ) : this(nickname, null, null)

    var id: String? = null
    var birthday: Long? = null
    var email: String? = null
    var phone: String? = null
    var password: String? = null
    var salt: String? = null
    var roleID: String? = null
    var lastLoginTime: Long? = null
    var createTime: Long? = null

    fun getAccount(): String {
        return if (email != null) {
            email!!
        } else {
            phone!!
        }
    }

    fun getRealPortraitUrl() = Config.ServerPath + headPortrait

}