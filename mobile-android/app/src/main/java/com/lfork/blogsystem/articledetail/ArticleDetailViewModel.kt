package com.lfork.blogsystem.articledetail

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.graphics.drawable.Drawable
import android.text.TextUtils
import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.BlogApplication.Companion.appFixedThreadPool
import com.lfork.blogsystem.BlogApplication.Companion.token
import com.lfork.blogsystem.R
import com.lfork.blogsystem.base.viewmodel.BaseViewModel
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.article.ArticleDataRepository
import com.lfork.blogsystem.data.article.ArticleResponse
import com.lfork.blogsystem.data.comment.Comment
import com.lfork.blogsystem.data.comment.CommentDataRepository
import com.lfork.blogsystem.data.comment.CommentListResponse
import com.lfork.blogsystem.data.user.User
import com.lfork.blogsystem.data.user.UserDataRepository
import com.lfork.blogsystem.utils.TimeUtil
import java.util.*
import kotlin.collections.ArrayList

class ArticleDetailViewModel(var articleId: String) : BaseViewModel() {
    var account: String? = null

    val title = ObservableField<String>("")

    val portraitUrl = ObservableField<String>("")

    val username = ObservableField<String>("L.Fork")

    val wordCount = ObservableField<String>("word count:100")

    val readingNumber = ObservableField<String>("Reading Number:200")

    val time = ObservableField<String>("")

    val like = ObservableInt(20)

    var likeIcon =
        ObservableField<Drawable>(BlogApplication.context!!.resources.getDrawable(R.drawable.ic_like_black_24dp))

    val star = ObservableInt(20)

    var starIcon =   ObservableField<Drawable>(BlogApplication.context!!.resources.getDrawable(R.drawable.ic_star_border_black_24dp))


    val placeDrawableId = ObservableInt(R.drawable.ic_person_black_24dp)

    val userIsAuthor = ObservableBoolean(false)

    var navigator: ArticleContentNavigator? = null

    var commentNavigator: CommentNavigator? = null

    var comments: ArrayList<Comment>? = null

    private var commentNextPageNumber = 1
    private val commentNextPageSize = 10


    fun start() {
        loadArticle()
        loadComments()
    }

    private fun loadArticle() {
        val callback = object : DataCallback<ArticleResponse> {
            override fun succeed(data: ArticleResponse) {
                time.set(TimeUtil.getStandardTime(Date(data.createTime!!.toLong())))
                title.set(data.title)
                wordCount.set("word count:${data.content?.length}")
                if (data.authorId == BlogApplication.userId){
                    userIsAuthor.set(true)
                }
                navigator?.showContent(data)
                if (comments != null) {
                    dataIsLoading.set(false)
                    dataLoadError.set(false)
                }
            }

            override fun failed(code: Int, log: String) {
                navigator?.showTips(log)
                dataLoadError.set(true)

            }
        }
        ArticleDataRepository.getArticle(articleId, callback)
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
        CommentDataRepository.addSubComment(token!!, parent.id!!, c.content!!, callback)
    }


    private fun loadComments() {
        val callback = object : DataCallback<CommentListResponse> {
            override fun succeed(data: CommentListResponse) {
                comments = data.result
                commentNavigator?.refreshComments(data.result!!)

                if (!TextUtils.isEmpty(time.get())) {
                    dataIsLoading.set(false)
                    dataLoadError.set(false)
                }
            }

            override fun failed(code: Int, log: String) {
                navigator?.showTips(log)
                dataLoadError.set(true)
            }
        }
        CommentDataRepository.getComments(
            commentNextPageNumber,
            commentNextPageSize,
            articleId,
            callback
        )
    }

    fun deleteComment(c: Comment) {
        val callback = object : DataCallback<String> {
            override fun succeed(data: String) {
                commentNavigator?.deleteComment(c)
            }

            override fun failed(code: Int, log: String) {
                navigator?.showTips(log)
            }
        }

        CommentDataRepository.deleteComment(token!!, c.id!!, callback)
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

    /**
     * 收藏
     */
    fun starArticle() {
        val callback = object : DataCallback<String> {
            override fun succeed(data: String) {
                star.set(star.get() + 1)
                starIcon.set(BlogApplication.context!!.resources.getDrawable(R.drawable.ic_stared_green_24dp))
                commentNavigator?.showTips("Star OK")
            }

            override fun failed(code: Int, log: String) {
                navigator?.showTips(log)
            }
        }
        ArticleDataRepository.starArticle(token!!, articleId, callback)
    }


    fun deleteArticle() {
        val callback = object : DataCallback<String> {
            override fun succeed(data: String) {
                navigator?.showTips("Deleted")
            }

            override fun failed(code: Int, log: String) {
                navigator?.showTips(log)
            }
        }
        ArticleDataRepository.deleteArticle(token!!, articleId, callback)
    }


    fun likeArticle() {
        val callback = object : DataCallback<String> {
            override fun succeed(data: String) {
                like.set(like.get() + 1)
                likeIcon .set(BlogApplication.context!!.resources.getDrawable(R.drawable.ic_liked_green_24dp))
                commentNavigator?.showTips("Like OK")
            }

            override fun failed(code: Int, log: String) {
                navigator?.showTips(log)
            }
        }
        ArticleDataRepository.likeArticle(token!!, articleId, callback)
    }

    fun followAuthor() {

    }

    val htmlTestData =
        ObservableField<String>("<html> <body><H1>Hello world</H1><H2>😂\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4><H1>Hello world</H1><H2>\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02</H2><H3>content  contentcontent</H3><H4>中文测试哈哈哈哈</H4></body>   </html>")
}
