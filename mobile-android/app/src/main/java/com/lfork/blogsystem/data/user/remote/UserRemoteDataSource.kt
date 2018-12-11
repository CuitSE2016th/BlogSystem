package com.lfork.blogsystem.data.user.remote

import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.base.network.MyRetrofitCallBack
import com.lfork.blogsystem.data.user.User
import com.lfork.blogsystem.data.user.UserDataSource
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 *
 * Created by 98620 on 2018/11/15.
 */
class UserRemoteDataSource : UserDataSource {
    override fun updateUserInfo(
        newUser: User,
        account: String,
        token: String,
        callback: DataCallback<User>
    ) {

        api.updateUserInfo(account, token, "" + newUser.nickname, null)
            .enqueue(MyRetrofitCallBack(callback))
    }

    override fun updateUserPortrait(
        pic: File,
        account: String,
        token: String,
        callback: DataCallback<User>
    ) {
        val fileBody = RequestBody.create(MediaType.parse("image/*"),pic)
        val photo = MultipartBody.Part.createFormData("pic", pic.name, fileBody)
        api.uploadPortrait(RequestBody.create(null, account), RequestBody.create(null, token), photo)
            .enqueue(MyRetrofitCallBack(callback))
    }

    override fun getUserInfo(account: String, token: String, callback: DataCallback<User>) {
        api.getUserInfo(account, token).enqueue(MyRetrofitCallBack(callback))
    }

    private val api: UserApi = UserApi.create()

    override fun login(account: String, password: String, callback: DataCallback<String>) {
        api.login(account, password).enqueue(MyRetrofitCallBack(callback))
    }

    override fun register(
        account: String,
        password: String,
        verifyCode: String,
        callback: DataCallback<String>
    ) {
        api.register(account, verifyCode, password).enqueue(MyRetrofitCallBack(callback))
    }

    override fun getVerificationCode(account: String, callback: DataCallback<String>) {
        api.getVerificationCode(account).enqueue(MyRetrofitCallBack(callback))
    }

}