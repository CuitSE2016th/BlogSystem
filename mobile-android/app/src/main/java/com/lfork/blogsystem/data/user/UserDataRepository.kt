package com.lfork.blogsystem.data.user

import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.data.common.DataCallback
import com.lfork.blogsystem.data.user.remote.UserRemoteDataSource
import com.lfork.blogsystem.utils.StringValidation

/**
 *
 * Created by 98620 on 2018/11/15.
 */
object UserDataRepository : UserDataSource {
    private val remoteDataSource = UserRemoteDataSource()

    var isSignIn = false

    var userCache: User? = null

    var followings: List<User>? = null

    var followers: List<User>? = null

    var userCacheIsDirty = true;

    override fun login(account: String, password: String, callback: DataCallback<String>) {
        remoteDataSource.login(account, password, object : DataCallback<String> {
            override fun succeed(data: String) {
                BlogApplication.token = data
                userCache = User()
                if(StringValidation.isEmailValid(account)){
                    userCache!!.email = account
                } else {
                    userCache!!.phone = account
                }

                callback.succeed(data)
            }

            override fun failed(code: Int, log: String) {
                callback.failed(code, log)
            }
        })
    }

    override fun register(account: String, password: String, verifyCode: String, callback: DataCallback<String>) {
        remoteDataSource.register(account, password, verifyCode, callback)
    }

    override fun getVerificationCode(account: String, callback: DataCallback<String>) {
        remoteDataSource.getVerificationCode(account, callback)
    }

    fun getCurrentUserInfo(callback: DataCallback<User>) {

        if (!userCacheIsDirty) {
            callback.succeed(userCache!!)
            return
        }


        if (userCache != null) {
            callback.succeed(userCache!!)
            userCacheIsDirty = false
        } else {
            callback.failed(-1, "用户未登录")
        }


//        remoteDataSource.getCurrentUserInfo(callback)
    }


}