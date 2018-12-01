package com.lfork.blogsystem.main.my

import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.base.viewmodel.UserViewModel
import com.lfork.blogsystem.base.network.DataCallback
import com.lfork.blogsystem.data.user.User
import com.lfork.blogsystem.data.user.UserDataRepository

class MyViewModel : UserViewModel() {


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
            portraitUrl.set(data.getRealPortraitUrl())
        }
    }


    fun refreshData() {
        if (BlogApplication.isSignIn) {
            UserDataRepository.getUserInfo(
                UserDataRepository.userCache.getAccount(),BlogApplication.token!!,
                object : DataCallback<User> {
                    override fun succeed(data: User) {
                        initBasicInfo()
                    }

                    override fun failed(code: Int, log: String) {
                        navigator?.showTips(log)
                    }
                })
        }
    }
}
