package com.lfork.blogsystem.articledetail

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.graphics.drawable.Drawable
import android.text.TextUtils
import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.BlogApplication.Companion.token
import com.lfork.blogsystem.R
import com.lfork.blogsystem.base.viewmodel.BaseViewModel
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.article.ArticleDataRepository
import com.lfork.blogsystem.data.article.ArticleDetailResponse
import com.lfork.blogsystem.data.comment.Comment
import com.lfork.blogsystem.data.comment.CommentDataRepository
import com.lfork.blogsystem.data.comment.CommentListResponse
import com.lfork.blogsystem.data.user.User
import com.lfork.blogsystem.data.user.UserDataRepository
import kotlin.collections.ArrayList

class ArticleDetailViewModel(var articleId: String) : BaseViewModel() {
    var account: String? = null

    val title = ObservableField<String>("")

    val portraitUrl = ObservableField<String>("")

    val username = ObservableField<String>("L.Fork")

    val wordCount = ObservableField<String>("word count:100")

    val readingNumber = ObservableField<String>("Reading Number:200")

    val time = ObservableField<String>("")

    var authorId: String?=null

    var stared = false

    var liked = false

    val like = ObservableInt(0)

    var isFollowedAuthor: Boolean? = null

    val followText = ObservableField<String>("Follow")

    var likeIcon =
        ObservableField<Drawable>(BlogApplication.context!!.resources.getDrawable(R.drawable.ic_like_black_24dp))

    val star = ObservableInt(0)

    var starIcon =
        ObservableField<Drawable>(BlogApplication.context!!.resources.getDrawable(R.drawable.ic_star_border_black_24dp))


    val placeDrawableId = ObservableInt(R.drawable.ic_person_black_24dp)

    val userIsAuthor = ObservableBoolean(false)

    var navigator: ArticleContentNavigator? = null

    var commentNavigator: CommentNavigator? = null

    var comments: ArrayList<Comment>? = null

    private var commentNextPageNumber = 1
    val commentNextPageSize = 10


    fun start() {
        loadArticle()
        loadComments()
    }



    private fun hideDataIsLoading() {
        if (comments != null && !TextUtils.isEmpty(time.get()) && isFollowedAuthor != null) {
            dataIsLoading.set(false)
            dataLoadError.set(false)
        }
    }

    private fun showDataLoadIsError() {
        dataLoadError.set(true)
    }

    private fun setStaredStatus(increaseNumber:Int=1) {
        star.set(star.get() + increaseNumber)
        stared = true
        starIcon.set(BlogApplication.context!!.resources.getDrawable(R.drawable.ic_stared_green_24dp))

    }

    private fun setUnStaredStatus(decreaseNumber:Int=1) {
        star.set(star.get() - decreaseNumber)
        stared = false
        starIcon.set(BlogApplication.context!!.resources.getDrawable(R.drawable.ic_star_border_black_24dp))
    }

    private fun setLikedStatus(increaseNumber:Int=1) {
        like.set(like.get()+increaseNumber)
        liked = true
        likeIcon.set(BlogApplication.context!!.resources.getDrawable(R.drawable.ic_liked_green_24dp))

    }

    private fun setUnLikedStatus(decreaseNumber:Int=1) {
        like.set(like.get() - decreaseNumber)
        liked = false
        likeIcon.set(BlogApplication.context!!.resources.getDrawable(R.drawable.ic_like_black_24dp))
    }


    private fun loadArticle() {
        if (authorId != null) {
            return
        }
        val callback = object : DataCallback<ArticleDetailResponse> {
            override fun succeed(data: ArticleDetailResponse) {
//                time.set(TimeUtil.getStandardTime(Date(data.createTime!!.toLong())))
                time.set(data.time)
                if (data.stared == true) {
                    setStaredStatus((data.starCount ?: 0))
                } else {
                    setUnStaredStatus(data.starCount ?: 0)
                }

                if (data.liked == true) {
                    setLikedStatus(data.likeCount ?: 0)
                } else {
                    setUnLikedStatus(data.likeCount ?: 0)
                }

                title.set(data.title)
                authorId = data.authorId!!
                portraitUrl.set(data.imageUrl)
                username.set(data.username)
                wordCount.set("word count:${data.content?.length}")
                if (data.authorId == BlogApplication.userId) {
                    userIsAuthor.set(true)
                }
                navigator?.showContent(data)
                loadFollowStatus()
                hideDataIsLoading()
            }

            override fun failed(code: Int, log: String) {
                navigator?.showTips(log)
                showDataLoadIsError()

            }
        }
        ArticleDataRepository.getArticle(articleId, callback)
    }

    private fun setFollowedStatus() {
        isFollowedAuthor = true
        followText.set("Followed")
    }

    private fun setUnFollowedStatus() {
        isFollowedAuthor = false
        followText.set("Follow")
    }

    private fun loadFollowStatus() {

        if (!BlogApplication.isSignIn) {
            setUnFollowedStatus()
            hideDataIsLoading()
            return
        }

        val callback = object : DataCallback<Boolean> {
            override fun succeed(data: Boolean) {

                if (data) {
                    setFollowedStatus()
                } else {
                    setUnFollowedStatus()
                }
                hideDataIsLoading()
            }

            override fun failed(code: Int, log: String) {
                navigator?.showTips(log)
            }
        }

        UserDataRepository.isFollowed(token!!, BlogApplication.userId!!, authorId!!, callback)

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

        CommentDataRepository.addComment(BlogApplication.token!!, c, callback)
    }


    fun addSubComment(position: Int, parent: Comment, c: Comment) {
        val callback = object : DataCallback<Comment> {
            override fun succeed(data: Comment) {
                commentNavigator?.addSubComment(position, parent, data)
            }

            override fun failed(code: Int, log: String) {
                navigator?.showTips(log)
            }
        }
        CommentDataRepository.addSubComment(token!!, c, callback)
    }

    fun loadMoreComments() {

        val callback = object : DataCallback<CommentListResponse> {
            override fun succeed(data: CommentListResponse) {
                commentNextPageNumber++
                comments?.addAll(data.result ?: ArrayList())
                commentNavigator?.loadMoreComments(data.result ?: ArrayList())
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

    private fun loadComments() {

        if ((comments?.size ?: 0) > 0) {
            return
        }

        val callback = object : DataCallback<CommentListResponse> {
            override fun succeed(data: CommentListResponse) {
                commentNextPageNumber++
                comments = data.result
                commentNavigator?.refreshComments(data.result!!)
                hideDataIsLoading()

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

    fun deleteComment(position: Int, c: Comment) {
        val callback = object : DataCallback<String> {
            override fun succeed(data: String) {
                commentNavigator?.deleteComment(position, c)
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


    fun followOrUnFollowAuthor() {
        if (isFollowedAuthor == true) {
            val unFollowCallback = object : DataCallback<String> {
                override fun succeed(data: String) {
                    navigator?.showTips("Unfollowed")
                    setUnFollowedStatus()
                }

                override fun failed(code: Int, log: String) {
                    navigator?.showTips(log)
                }
            }

            UserDataRepository.unFollow(authorId!!, token!!, unFollowCallback)

        } else {
            val followCallback = object : DataCallback<String> {
                override fun succeed(data: String) {
                    navigator?.showTips("Followed")
                    setFollowedStatus()
                }

                override fun failed(code: Int, log: String) {
                    navigator?.showTips(log)
                }
            }

            UserDataRepository.follow(authorId!!, token!!, followCallback)
        }
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

    /**
     * 收藏
     */
    fun starOrUnStarArticle() {
        if (!stared) {
            val starCallback = object : DataCallback<String> {
                override fun succeed(data: String) {

                    setStaredStatus()
                }

                override fun failed(code: Int, log: String) {
                    navigator?.showTips(log)
                }
            }
            ArticleDataRepository.starArticle(token!!, articleId, starCallback)
        } else {
            val unStarCallback = object : DataCallback<String> {
                override fun succeed(data: String) {
                    setUnStaredStatus()
                }

                override fun failed(code: Int, log: String) {
                    navigator?.showTips(log)
                }
            }

            ArticleDataRepository.unStarArticle(token!!, articleId, unStarCallback)
        }

    }


    /**
     * 点赞
     */
    fun likeOrUnlikeArticle() {
        if (!liked) {
            val callback = object : DataCallback<String> {
                override fun succeed(data: String) {
                    setLikedStatus()
                }

                override fun failed(code: Int, log: String) {
                    navigator?.showTips(log)
                }
            }
            ArticleDataRepository.likeArticle(token!!, articleId, callback)

        } else {
            val callback = object : DataCallback<String> {
                override fun succeed(data: String) {

                    setUnLikedStatus()
                }

                override fun failed(code: Int, log: String) {
                    navigator?.showTips(log)
                }
            }
            ArticleDataRepository.unLikeArticle(token!!, articleId, callback)

        }
    }
}
