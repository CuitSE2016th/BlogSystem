package com.lfork.blogsystem.data.user

data class User(var nickname: String?, var headPortrait: String?, var sex: String? = null) {
    constructor() : this(null, null, null)
    var id: String? = null
    var birthday: Long? = null
    var email: String? = null
    var phone: String? = null
    var password: String? = null
    var salt: String? = null
    var roleID: String? = null
    var lastLoginTime: Long? = null
    var createTime: Long? = null


    fun getAccount():String{
        if (email != null) {
            return email!!
        } else{
            return phone!!
        }
    }

}