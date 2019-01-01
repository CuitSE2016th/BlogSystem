package com.lfork.blogsystem.data.user

import com.lfork.blogsystem.data.DataCallback
import java.io.File

/**
 *
 * Created by 98620 on 2018/11/15.
 */
interface UserDataSource {

    /**
     * @param account Phone or Email
     */
    fun login(account: String, password: String, callback: DataCallback<User>)


    fun register(
        account: String,
        password: String,
        verifyCode: String,
        callback: DataCallback<String>
    )

    fun getUserInfo(account: String, token: String, callback: DataCallback<User>)


    fun updateUserInfo(newUser: User, account: String, token: String, callback: DataCallback<User>)

    fun updateUserPortrait(
        pic: File,
        account: String,
        token: String,
        callback: DataCallback<String>
    )

    /**
     * 需要做好限60秒一次的限制
     */
    fun getVerificationCode(account: String, callback: DataCallback<String>)


    fun unFollow(
        beUnFollowedId: String,
        token: String,
        callback: DataCallback<String>
    );

    fun follow(
        beFollowedId: String,
        token: String,
        callback: DataCallback<String>
    )

    fun isFollowed(
        token: String,
        followerId: String,
        beFollowedId: String,
        callback: DataCallback<Boolean>
    )

    //分页，userId,
//    fun getFollowings();

    //分页，userId,
    fun getFollowers(account: String, token: String, callback: DataCallback<List<User>>);

    fun getFollowings(account: String, token: String, callback: DataCallback<List<User>>)

}