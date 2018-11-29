package com.lfork.blogsystem.main.my

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField
import android.util.Log
import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.data.common.network.DataCallback
import com.lfork.blogsystem.data.user.User
import com.lfork.blogsystem.data.user.UserDataRepository

class MyViewModel : ViewModel() {
    val username = ObservableField<String>("")

    val followingCount = ObservableField<String>("")

    val followersCount = ObservableField<String>("")

    val wordCount = ObservableField<String>("")


    var navigator: MyNavigator? = null

    fun start(nickname: String) {
        this.username.set(nickname)
        initBasicInfo()
    }

    fun initBasicInfo() {
        if (BlogApplication.isSignIn) {
            val data = UserDataRepository.userCache
            when {
                data.nickname != null -> username.set(data.nickname)
                data.email != null -> username.set(data.email)
                data.phone != null -> username.set(data.phone)
            }
        }
    }

    fun registerNavigator(navigator: MyNavigator) {
        this.navigator = navigator
    }

    fun unregisterNavigator() {
        this.navigator = null
    }

    fun refreshData() {
        if (BlogApplication.isSignIn) {
            Thread {
                UserDataRepository.getUserInfo(UserDataRepository.userCache.getAccount(), object : DataCallback<User> {
                    override fun succeed(data: User) {
                        initBasicInfo()
                    }

                    override fun failed(code: Int, log: String) {
                        navigator?.showTips(log)
                    }
                })
            }.start()
        }
    }
}
