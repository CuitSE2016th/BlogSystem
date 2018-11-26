package com.lfork.blogsystem.main.my

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField
import android.provider.ContactsContract
import com.lfork.blogsystem.data.common.DataCallback
import com.lfork.blogsystem.data.user.User
import com.lfork.blogsystem.data.user.UserDataRepository
import com.lfork.blogsystem.utils.ToastUtil

class MyViewModel : ViewModel() {
    val nickname = ObservableField<String>("aaaaa")

    val followingCount = ObservableField<String>("")

    val followersCount = ObservableField<String>("")

    val wordCount = ObservableField<String>("")


    var navigator: MyNavigator? = null

    fun init(nickname: String) {
        this.nickname.set(nickname)
    }

    fun registerNavigator(navigator: MyNavigator) {
        this.navigator = navigator
    }

    fun unregisterNavigator() {
        this.navigator = null
    }

    fun refreshData() {
        Thread {
            UserDataRepository.getCurrentUserInfo(object : DataCallback<User> {
                override fun succeed(data: User) {
                    if (data.nickname != null) {
                        nickname.set(data.nickname)
                    } else if (data.email != null) {
                        nickname.set(data.email)
                    } else if (data.phone != null) {
                        nickname.set(data.phone)
                    }
                }

                override fun failed(code: Int, log: String) {
                    navigator?.showTips(log)
                }
            })
        }.start()

    }
}
