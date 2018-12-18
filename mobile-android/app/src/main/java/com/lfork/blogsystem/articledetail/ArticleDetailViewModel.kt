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

    val htmlTestData = ObservableField<String>("\n" +
            "\n" +
            "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "  <head>\n" +
            "  <title>BlogSystem/简化版的RUP模型.md at master · CuitSE2016th/BlogSystem</title>\n" +
            "   \n" +
            "\n" +
            "  </head>\n" +
            "\n" +
            "  <body class=\"logged-in env-production page-blob\">\n" +
            "    \n" +
            "\n" +
            "      \n" +
            "  <div id=\"readme\" class=\"readme blob instapaper_body\">\n" +
            "    <article class=\"markdown-body entry-content\" itemprop=\"text\"><h1><a id=\"user-content-前言\" class=\"anchor\" aria-hidden=\"true\" href=\"#前言\"><svg class=\"octicon octicon-link\" viewBox=\"0 0 16 16\" version=\"1.1\" width=\"16\" height=\"16\" aria-hidden=\"true\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a>前言</h1>\n" +
            "<h2><a id=\"user-content-参考的模型\" class=\"anchor\" aria-hidden=\"true\" href=\"#参考的模型\"><svg class=\"octicon octicon-link\" viewBox=\"0 0 16 16\" version=\"1.1\" width=\"16\" height=\"16\" aria-hidden=\"true\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a>参考的模型</h2>\n" +
            "<p>RUP的迭代开发、增量模型、快速原型模式、小型瀑布流模式。<br><br>\n" +
            "可以说有点接近敏捷开发了、但是我们的沟通是个问题，所以实际上很难采用敏捷开发，<br>\n" +
            "因为敏捷开发需要较强的团队沟通，我觉得不容易达到，我我认为还是需要写一些必要的<br>\n" +
            "文档来降低沟通成本</p>\n" +
            "<h1><a id=\"user-content-开发流程\" class=\"anchor\" aria-hidden=\"true\" href=\"#开发流程\"><svg class=\"octicon octicon-link\" viewBox=\"0 0 16 16\" version=\"1.1\" width=\"16\" height=\"16\" aria-hidden=\"true\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a>开发流程</h1>\n" +
            "<h2><a id=\"user-content-第一次迭代\" class=\"anchor\" aria-hidden=\"true\" href=\"#第一次迭代\"><svg class=\"octicon octicon-link\" viewBox=\"0 0 16 16\" version=\"1.1\" width=\"16\" height=\"16\" aria-hidden=\"true\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a>第一次迭代：</h2>\n" +
            "<ul>\n" +
            "<li>-&gt;系统层次图(总体设计简化版)</li>\n" +
            "<li>-&gt;一个模块的原型(UI简要设计)【必须】</li>\n" +
            "<li>-&gt;一个模块的CIM与PIM(可以简画)、</li>\n" +
            "<li>-&gt;一个模块的数据库设计(E-R图)【必须】、</li>\n" +
            "<li>-&gt;一个模块的接口设计【必须】</li>\n" +
            "<li>-&gt;开发</li>\n" +
            "<li>-&gt;单元测试</li>\n" +
            "</ul>\n" +
            "<h2><a id=\"user-content-第二次迭代\" class=\"anchor\" aria-hidden=\"true\" href=\"#第二次迭代\"><svg class=\"octicon octicon-link\" viewBox=\"0 0 16 16\" version=\"1.1\" width=\"16\" height=\"16\" aria-hidden=\"true\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a>第二次迭代：</h2>\n" +
            "<ul>\n" +
            "<li>-&gt;一个模块的原型(UI简要设计)【必须】</li>\n" +
            "<li>-&gt;一个模块的CIM与PIM(可以简画)、</li>\n" +
            "<li>-&gt;一个模块的数据库设计(E-R图)【必须】、</li>\n" +
            "<li>-&gt;一个模块的接口设计【必须】</li>\n" +
            "<li>-&gt;开发</li>\n" +
            "<li>-&gt;单元测试</li>\n" +
            "<li>-&gt;集成测试</li>\n" +
            "</ul>\n" +
            "<p><em><strong>……….</strong></em></p>\n" +
            "<h2><a id=\"user-content-第n-1次迭代\" class=\"anchor\" aria-hidden=\"true\" href=\"#第n-1次迭代\"><svg class=\"octicon octicon-link\" viewBox=\"0 0 16 16\" version=\"1.1\" width=\"16\" height=\"16\" aria-hidden=\"true\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a>第N-1次迭代：</h2>\n" +
            "<ul>\n" +
            "<li>-&gt;一个模块的原型(UI简要设计)【必须】</li>\n" +
            "<li>-&gt;一个模块的CIM与PIM(可以简画)、</li>\n" +
            "<li>-&gt;一个模块的数据库设计(E-R图)【必须】、</li>\n" +
            "<li>-&gt;一个模块的接口设计【必须】</li>\n" +
            "<li>-&gt;开发</li>\n" +
            "<li>-&gt;单元测试</li>\n" +
            "<li>-&gt;集成测试</li>\n" +
            "</ul>\n" +
            "<h2><a id=\"user-content-第n次迭代\" class=\"anchor\" aria-hidden=\"true\" href=\"#第n次迭代\"><svg class=\"octicon octicon-link\" viewBox=\"0 0 16 16\" version=\"1.1\" width=\"16\" height=\"16\" aria-hidden=\"true\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a>第N次迭代：</h2>\n" +
            "<ul>\n" +
            "<li>-&gt;一个模块的原型(UI简要设计)【必须】</li>\n" +
            "<li>-&gt;一个模块的CIM与PIM(可以简画)、</li>\n" +
            "<li>-&gt;一个模块的数据库设计(E-R图)【必须】、</li>\n" +
            "<li>-&gt;一个模块的接口设计【必须】</li>\n" +
            "<li>-&gt;开发</li>\n" +
            "<li>-&gt;单元测试</li>\n" +
            "<li>-&gt;集成测试</li>\n" +
            "<li>-&gt;系统测试</li>\n" +
            "</ul>\n" +
            "</article>\n" +
            "  </div>\n" +
            "\n" +
            "    </div>\n" +
            "\n" +
            "  \n" +
            "\n" +
            "  <details class=\"details-reset details-overlay details-overlay-dark\">\n" +
            "    <summary data-hotkey=\"l\" aria-label=\"Jump to line\"></summary>\n" +
            "    <details-dialog class=\"Box Box--overlay d-flex flex-column anim-fade-in fast linejump\" aria-label=\"Jump to line\">\n" +
            "      <!-- '\"` --><!-- </textarea></xmp> --></option></form>\n" +
            "  </body>\n" +
            "</html>\n" +
            "\n")

}
