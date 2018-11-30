package com.lfork.blogsystem.base.viewmodel

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.util.Log
import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.R
import com.lfork.blogsystem.base.viewmodel.Navigator
import com.lfork.blogsystem.data.common.network.DataCallback
import com.lfork.blogsystem.data.user.User
import com.lfork.blogsystem.data.user.UserDataRepository

abstract class UserViewModel : ViewModel() {

    val portraitUrl = ObservableField<String>("")

    val username = ObservableField<String>("")

    val followingCount = ObservableField<String>("")

    val followersCount = ObservableField<String>("")

    val wordCount = ObservableField<String>("")

    val placeDrawableId = ObservableInt(R.drawable.ic_person_black_24dp)


    var navigator:Navigator?=null

    fun registerNavigator(navigator:Navigator){
        this.navigator = navigator
    }

    fun unregisterNavigator(){
        navigator = null
    }

    fun refreshUserInfo() {
        UserDataRepository.getUserInfo(
            UserDataRepository.userCache.getAccount(),
            object : DataCallback<User> {
                override fun succeed(data: User) {
                    when {
                        data.nickname != null -> username.set(data.nickname)
                        data.email != null -> username.set(data.email)
                        data.phone != null -> username.set(data.phone)
                    }
                    portraitUrl.set(data.headPortrait)
                }

                override fun failed(code: Int, log: String) {
                    navigator?.showTips(log)
                }
            })
    }


}
