package com.lfork.blogsystem.data.user.remote

import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.data.common.network.DataCallback
import com.lfork.blogsystem.data.common.network.MyRetrofitCallBack
import com.lfork.blogsystem.data.user.User
import com.lfork.blogsystem.data.user.UserDataSource

/**
 *
 * Created by 98620 on 2018/11/15.
 */
class UserRemoteDataSource:UserDataSource{
    override fun getUserInfo(account: String, callback: DataCallback<User>) {
        api.getUserInfo(account, BlogApplication.token!!).enqueue(MyRetrofitCallBack(callback))
    }

    private val api: UserApi = UserApi.create()

    override fun login(account: String, password: String, callback: DataCallback<String>) {
        api.login(account, password).enqueue(MyRetrofitCallBack(callback))
    }

    override fun register(account: String, password: String, verifyCode: String, callback: DataCallback<String>) {
        api.register(account, verifyCode, password).enqueue(MyRetrofitCallBack(callback))
    }

    override fun getVerificationCode(account: String, callback: DataCallback<String>) {
        api.getVerificationCode(account).enqueue(MyRetrofitCallBack(callback))
    }

}