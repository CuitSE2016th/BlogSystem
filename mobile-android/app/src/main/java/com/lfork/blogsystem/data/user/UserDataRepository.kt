package com.lfork.blogsystem.data.user

import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.data.common.network.DataCallback
import com.lfork.blogsystem.data.user.remote.UserRemoteDataSource
import com.lfork.blogsystem.utils.StringValidation

/**
 *
 * Created by 98620 on 2018/11/15.
 */
object UserDataRepository : UserDataSource {

    private val remoteDataSource = UserRemoteDataSource()


    var userCache: User = User()

    var followings: List<User>? = null

    var followers: List<User>? = null

    private var userCacheIsDirty = true


    override fun login(account: String, password: String, callback: DataCallback<String>) {
        remoteDataSource.login(account, password, object : DataCallback<String> {
            override fun succeed(data: String) {

                if (StringValidation.isEmailValid(account)) {
                    userCache.email = account
                } else {
                    userCache.phone = account
                }
                BlogApplication.isSignIn = true
                updateCoreUserInfo()
                BlogApplication.saveToken(data)
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


    fun refreshUserInfor() {
        userCacheIsDirty = true
    }

    override fun getUserInfo(account: String, callback: DataCallback<User>) {
        if (account == userCache.phone || account == userCache.email) {
            if (!userCacheIsDirty) {
                callback.succeed(userCache)
                return
            } else {
                remoteDataSource.getUserInfo(account, object : DataCallback<User> {
                    override fun succeed(data: User) {
                        userCache = data
                        updateCoreUserInfo()
                        userCacheIsDirty = false
                        callback.succeed(data)
                    }

                    override fun failed(code: Int, log: String) {
                        callback.failed(code, log)
                    }
                })
            }
        } else {
            remoteDataSource.getUserInfo(account, callback)
        }
    }

    fun initBasicUserInfo(email: String?, phone: String?, nickname: String?) {
        userCache.email = email
        userCache.phone = phone
        userCache.nickname = nickname
    }

    private fun updateCoreUserInfo() {
        if (userCache.email != null) {
            BlogApplication.saveEmail(userCache.email!!)
        }

        if (userCache.phone != null) {
            BlogApplication.savePhone(userCache.phone!!)
        }

        if (userCache.nickname != null) {
            BlogApplication.saveNickname(userCache.nickname!!)
        }

    }
}