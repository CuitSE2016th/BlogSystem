package com.lfork.blogsystem.data.user

import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.base.network.DataCallback
import com.lfork.blogsystem.data.user.remote.UserRemoteDataSource
import com.lfork.blogsystem.utils.StringValidation
import java.io.File

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
                updateCoreUserInfo(null)
                BlogApplication.saveToken(data)
                callback.succeed(data)
            }

            override fun failed(code: Int, log: String) {
                callback.failed(code, log)
            }
        })
    }

    override fun register(
        account: String,
        password: String,
        verifyCode: String,
        callback: DataCallback<String>
    ) {
        remoteDataSource.register(account, password, verifyCode, callback)
    }

    override fun getVerificationCode(account: String, callback: DataCallback<String>) {
        remoteDataSource.getVerificationCode(account, callback)
    }


    override fun getUserInfo(account: String, token: String, callback: DataCallback<User>) {
        if (account == userCache.phone || account == userCache.email) {
            if (!userCacheIsDirty) {
                callback.succeed(userCache)
                return
            } else {
                remoteDataSource.getUserInfo(account, token, object : DataCallback<User> {
                    override fun succeed(data: User) {

                        updateCoreUserInfo(data)

                        callback.succeed(data)
                    }

                    override fun failed(code: Int, log: String) {
                        callback.failed(code, log)
                    }
                })
            }
        } else {
            remoteDataSource.getUserInfo(account, token, callback)
        }
    }

    override fun updateUserInfo(
        newUser: User,
        account: String,
        token: String,
        callback: DataCallback<User>
    ) {
       remoteDataSource.updateUserInfo(newUser, account, token, object :DataCallback<User>{
           override fun succeed(data: User) {
               updateCoreUserInfo(data)
           }

           override fun failed(code: Int, log: String) {
               callback.failed(code, log)
           }
       })
    }

    override fun updateUserPortrait(
        pic: File,
        account: String,
        token: String,
        callback: DataCallback<User>
    ) {
       remoteDataSource.updateUserPortrait(pic, account, token,object :DataCallback<User>{
           override fun succeed(data: User) {
               updateCoreUserInfo(data)
           }

           override fun failed(code: Int, log: String) {
               callback.failed(code, log)
           }
       })
    }


    fun refreshUserInfor() {
        userCacheIsDirty = true
    }


    fun initBasicUserInfo(email: String?, phone: String?, nickname: String?) {
        userCache.email = email
        userCache.phone = phone
        userCache.nickname = nickname
    }

    private fun updateCoreUserInfo(data:User?) {

        if(data != null) {
            userCache = data
            userCacheIsDirty = false
        }

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