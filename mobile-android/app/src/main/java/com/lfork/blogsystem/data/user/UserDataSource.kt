package com.lfork.blogsystem.data.user

import com.lfork.blogsystem.data.common.DataCallback

/**
 *
 * Created by 98620 on 2018/11/15.
 */
interface UserDataSource {

    /**
     * @param account Phone or Email
     */
    fun login(account: String, password: String, callback: DataCallback<String>)

//    fun logout()

    fun register(account: String, password: String, verifyCode: String, callback: DataCallback<String>)

//    fun getCurrentUserInfo()

//    fun getFlowingListInfo()

//    fun updateUserInfo()

    /**
     * 需要做好限60秒一次的限制
     */
    fun getVerificationCode(account: String, callback: DataCallback<String>)

}