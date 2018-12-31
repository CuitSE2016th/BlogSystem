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
    override fun unFollow(
        beUnFollowedAccount: String,
        account: String,
        token: String,
        callback: DataCallback<String>
    ) {
        callback.succeed("UnFollowed")
    }

    override fun follow(
        beFollowedAccount: String,
        account: String,
        token: String,
        callback: DataCallback<String>
    ) {
        callback.succeed("Followed")
    }

    override fun getFollowings(
        account: String,
        token: String,
        callback: DataCallback<List<User>>
    ) {
        val items = ArrayList<User>(10);
        for (i in 1..10){
            val it = User()
            it.phone = "18628904485"
            it.nickname = "the number $i following"
            it.headPortrait = "images/580598104651543672840254.jpg"
            it.description="the ${i}th description ,length test.big Text test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test"
            items.add(it)
        }
        callback.succeed(items)
    }

    /**
     * 暂时不考虑分页加载
     */
    override fun getFollowers(account: String, token: String, callback: DataCallback<List<User>>) {
        val items = ArrayList<User>(10);
        for (i in 1..10){
            val it = User()
            it.phone = "18628904485"
            it.nickname = "the number $i follower"
            it.headPortrait = "images/580598104651543672840254.jpg"
            it.description="the ${i}th description ,length test.big Text test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test"
            items.add(it)
        }
        callback.succeed(items)
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
        callback: DataCallback<User>
    ) {
        val fileBody = RequestBody.create(MediaType.parse("image/*"),pic)
        val photo = MultipartBody.Part.createFormData("pic", pic.name, fileBody)
        api.uploadPortrait(RequestBody.create(null, account), token, photo)
            .enqueue(HTTPCallBack(callback))
    }

    override fun getUserInfo(account: String, token: String, callback: DataCallback<User>) {
        api.getUserInfo(account, token).enqueue(HTTPCallBack(callback))
    }

    private val api: UserApi = UserApi.create()

    override fun login(account: String, password: String, callback: DataCallback<String>) {
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