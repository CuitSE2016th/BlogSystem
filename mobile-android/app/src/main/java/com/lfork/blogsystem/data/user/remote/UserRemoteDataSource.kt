package com.lfork.blogsystem.data.user.remote

import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.base.network.HTTPCallBack
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
    override fun isFollowed(
        token: String,
        followerId: String,
        beFollowedId: String,
        callback: DataCallback<Boolean>
    ) {
        api.isFollowed(followerId, beFollowedId, token).enqueue(HTTPCallBack(callback))
    }

    override fun unFollow(
        beUnFollowedId: String,
        token: String,
        callback: DataCallback<String>
    ) {
        api.unFollow(beUnFollowedId, token).enqueue(HTTPCallBack(callback))
    }

    override fun follow(
        beFollowedId: String,
        token: String,
        callback: DataCallback<String>
    ) {
        api.follow(beFollowedId, token).enqueue(HTTPCallBack(callback))
    }

    override fun getFollowings(token: String, callback: DataCallback<ArrayList<User>>) {
        api.getFollowings(token).enqueue(HTTPCallBack(callback))
    }

    /**
     * 暂时不考虑分页加载
     */
    override fun getFollowers(token: String, callback: DataCallback<ArrayList<User>>) {
        api.getFollowers(token).enqueue(HTTPCallBack(callback))
    }

    override fun updateUserInfo(
        newUser: User,
        account: String,
        token: String,
        callback: DataCallback<User>
    ) {

        api.updateUserInfo(account, token, "" + newUser.nickname, null)
            .enqueue(HTTPCallBack(callback))
    }

    override fun updateUserPortrait(
        pic: File,
        account: String,
        token: String,
        callback: DataCallback<String>
    ) {
        val fileBody = RequestBody.create(MediaType.parse("image/*"), pic)
        val photo = MultipartBody.Part.createFormData("pic", pic.name, fileBody)
        api.uploadPortrait(RequestBody.create(null, account), token, photo)
            .enqueue(HTTPCallBack(callback))
    }

    override fun getUserInfo(account: String, token: String, callback: DataCallback<User>) {
        api.getUserInfo(account, token).enqueue(HTTPCallBack(callback))
    }

    private val api: UserApi = UserApi.create()

    override fun login(account: String, password: String, callback: DataCallback<User>) {
        api.login(account, password).enqueue(HTTPCallBack(callback))
    }

    override fun register(
        account: String,
        password: String,
        verifyCode: String,
        callback: DataCallback<String>
    ) {
        api.register(account, verifyCode, password).enqueue(HTTPCallBack(callback))
    }

    override fun getVerificationCode(account: String, callback: DataCallback<String>) {
        api.getVerificationCode(account).enqueue(HTTPCallBack(callback))
    }

}