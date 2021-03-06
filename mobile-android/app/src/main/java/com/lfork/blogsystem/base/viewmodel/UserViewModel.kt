package com.lfork.blogsystem.base.viewmodel

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.R
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.user.User
import com.lfork.blogsystem.data.user.UserDataRepository

abstract class UserViewModel(var account: String?) : ViewModel() {

    constructor() : this(null)

    val portraitUrl = ObservableField<String>("")

    val username = ObservableField<String>("")

    val followingCount = ObservableField<String>("")

    val followersCount = ObservableField<String>("")

    val wordCount = ObservableField<String>("")

    val isLogin = ObservableBoolean(false)

    val placeDrawableId = ObservableInt(R.drawable.avatar)

    var navigator: Navigator? = null

    fun registerNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    fun unregisterNavigator() {
        navigator = null
    }

    fun refreshUserInfo() {
        if (account == null)
            account = UserDataRepository.userCache.getAccount()
//        UserDataRepository.refreshUserInfo()
        UserDataRepository.getUserInfo(
            account!!, BlogApplication.token!!,
            object : DataCallback<User> {
                override fun succeed(data: User) {
                    when {
                        data.nickname != null -> username.set(data.nickname)
                        data.email != null -> username.set(data.email)
                        data.phone != null -> username.set(data.phone)
                    }
                    portraitUrl.set(data.getRealPortraitUrl())
                }

                override fun failed(code: Int, log: String) {
                    navigator?.showTips(log)
                }
            })
    }


}
