package com.lfork.blogsystem.articledetail

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.R
import com.lfork.blogsystem.base.viewmodel.Navigator
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.user.User
import com.lfork.blogsystem.data.user.UserDataRepository

class ArticleDetailViewModel : ViewModel() {
    var account: String?=null

    val title = ObservableField<String>("Rational Unified Process")

    val portraitUrl = ObservableField<String>("")

    val username = ObservableField<String>("L.Fork")

    val wordCount = ObservableField<String>("word count:100")

    val readingNumber = ObservableField<String>("Reading Number:200")

    val time = ObservableField<String>("2018/12/18")

    val like = ObservableField<String>("Likes(200K)")

    val star = ObservableField<String>("Star(100)")

    val placeDrawableId = ObservableInt(R.drawable.ic_person_black_24dp)

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

    val htmlTestData = ObservableField<String>("<html> <body><H1>Hello world</H1><H2>😂\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4></body>   </html>")

}
