package com.lfork.blogsystem.userinfoedit

import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.base.viewmodel.UserViewModel
import com.lfork.blogsystem.data.user.User
import com.lfork.blogsystem.data.user.UserDataRepository
import java.io.File

class UserInfoEditViewModel : UserViewModel() {

    fun updateUserInfo(){
        UserDataRepository.updateUserInfo(User(username.get()+""),UserDataRepository.userCache.getAccount(), BlogApplication.token!!, object :
            DataCallback<User> {
            override fun succeed(data: User) {
              username.set(data.nickname)
            }

            override fun failed(code: Int, log: String) {
                navigator?.showTips(log)
            }
        })
    }

    fun uploadPortrait(pic: File) {

        UserDataRepository.updateUserPortrait(pic,UserDataRepository.userCache.getAccount(), BlogApplication.token!!, object :
            DataCallback<User> {
            override fun succeed(data: User) {
                portraitUrl.set(data.getRealPortraitUrl())
                navigator?.showTips("Portrait Changed")
            }

            override fun failed(code: Int, log: String) {
                navigator?.showTips(log)
            }
        })
    }
}
