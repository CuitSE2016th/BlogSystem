package com.lfork.blogsystem.data.user.remote

import com.lfork.blogsystem.data.common.DataCallback
import com.lfork.blogsystem.data.common.network.MyRetrofitCallBack
import com.lfork.blogsystem.data.user.User
import com.lfork.blogsystem.data.user.UserDataSource

/**
 *
 * Created by 98620 on 2018/11/15.
 */
class UserRemoteDataSource:UserDataSource{

    private val api: UserApi = UserApi.create()

    override fun login(account: String, password: String, callback: DataCallback<User>) {
        api.login(account, password).enqueue(MyRetrofitCallBack(callback))
    }

    override fun register(account: String, password: String, verifyCode: String, callback: DataCallback<String>) {
        api.register(account, verifyCode, password).enqueue(MyRetrofitCallBack(callback))
    }

    override fun getVerifyCode(account: String, callback: DataCallback<String>) {
        api.getVerifyCode(account).enqueue(MyRetrofitCallBack(callback))
    }

    fun getUserInfo(callback: DataCallback<List<User>>){
        println("enene??1.1")
        api.getUserInfo().enqueue(MyRetrofitCallBack(callback))
    }


}