package com.lfork.blogsystem.articledetail

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.BlogApplication.Companion.appFixedThreadPool
import com.lfork.blogsystem.BlogApplication.Companion.token
import com.lfork.blogsystem.R
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.article.ArticleDataRepository
import com.lfork.blogsystem.data.article.ArticleResponse
import com.lfork.blogsystem.data.comment.Comment
import com.lfork.blogsystem.data.comment.CommentDataRepository
import com.lfork.blogsystem.data.comment.CommentListResponse
import com.lfork.blogsystem.data.user.User
import com.lfork.blogsystem.data.user.UserDataRepository

class ArticleDetailViewModel(var articleId: String) : ViewModel() {
    var account: String? = null

    val title = ObservableField<String>("Rational Unified Process")

    val portraitUrl = ObservableField<String>("")

    val username = ObservableField<String>("L.Fork")

    val wordCount = ObservableField<String>("word count:100")

    val readingNumber = ObservableField<String>("Reading Number:200")

    val time = ObservableField<String>("2018/12/18")

    val like = ObservableField<String>("Likes(200K)")

    val star = ObservableField<String>("Star(100)")

    val placeDrawableId = ObservableInt(R.drawable.ic_person_black_24dp)

    var navigator: ArticleContentNavigator? = null

    var commentNavigator: CommentNavigator? = null

    private var commentNextPageNumber = 1
    private val commentNextPageSize = 10


    fun start() {
        loadArticle()
        loadComments()
    }

    private fun loadArticle() {
        val callback = object : DataCallback<ArticleResponse> {
            override fun succeed(data: ArticleResponse) {
                time.set(data.createTime)
                title.set(data.title)
                navigator?.showContent(data)
            }

            override fun failed(code: Int, log: String) {
                navigator?.showTips(log)
            }
        }
        ArticleDataRepository.getArticle(articleId, callback)
    }

    fun refreshComments() {

    }

    fun addComment(c: Comment) {
        val callback = object : DataCallback<Comment> {
            override fun succeed(data: Comment) {
                commentNavigator?.addComment(data)
            }

            override fun failed(code: Int, log: String) {
                navigator?.showTips(log)
            }
        }

        CommentDataRepository.addComment(BlogApplication.token!!, articleId, c.content!!, callback)
    }


    fun addSubComment(parent: Comment, c: Comment) {
        val callback = object : DataCallback<Comment> {
            override fun succeed(data: Comment) {
                data.replyTo = c.replyTo

                commentNavigator?.addSubComment(parent, data)
            }

            override fun failed(code: Int, log: String) {
                navigator?.showTips(log)
            }
        }
        CommentDataRepository.addSubComment(token!!,parent.id!!,c.content!!,callback)
    }


    private fun loadComments() {
        val callback = object : DataCallback<CommentListResponse> {
            override fun succeed(data: CommentListResponse) {
                commentNavigator?.refreshComments(data.result!!)
            }

            override fun failed(code: Int, log: String) {
                navigator?.showTips(log)
            }
        }
        CommentDataRepository.getComments(
            commentNextPageNumber,
            commentNextPageSize,
            articleId,
            callback
        )
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

    val htmlTestData =
        ObservableField<String>("<html> <body><H1>Hello world</H1><H2>😂\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4></body>   </html>")

}
