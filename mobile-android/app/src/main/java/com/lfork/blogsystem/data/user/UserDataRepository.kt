package com.lfork.blogsystem.data.user

import com.lfork.blogsystem.data.common.DataCallback
import com.lfork.blogsystem.data.user.remote.UserRemoteDataSource

/**
 *
 * Created by 98620 on 2018/11/15.
 */
object UserDataRepository : UserDataSource {
    val remoteDataSource = UserRemoteDataSource()

    var userCache: User? = null

    var followings: List<User>? = null

    var followers: List<User>? = null

    override fun login(account: String, password: String, callback: DataCallback<User>) {
        remoteDataSource.login(account, password, callback)
    }

    override fun register(account: String, password: String, verifyCode: String, callback: DataCallback<String>) {
        remoteDataSource.register(account, password, verifyCode, callback)
    }

    override fun getVerifyCode(account: String, callback: DataCallback<String>) {
        remoteDataSource.getVerifyCode(account, callback)
    }

    fun getUserInfo(callback: DataCallback<List<User>>){
        remoteDataSource.getUserInfo(callback)
    }


}