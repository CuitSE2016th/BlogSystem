package com.lfork.blogsystem.common.mvp

import android.support.annotation.CallSuper
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.article.Article
import com.lfork.blogsystem.data.article.ArticleDataRepository
import com.lfork.blogsystem.data.article.ArticleListResponse
import com.lfork.blogsystem.data.user.UserDataRepository

/**
 *
 * Created by 98620 on 2018/12/15.
 */
open class ArticlePresenter(private var view: ArticleContract.View?) : BasePresenter(),
    ArticleContract.Presenter {

    val refreshDataCallBack2 = object : DataCallback<ArticleListResponse> {
        override fun succeed(data: ArticleListResponse) {

            if (data.result != null && data.result!!.size > 0) {
                totalPage = data.pageCount ?: 1
                nextPage = 2
                view?.refreshArticles(data.result ?: ArrayList())
            } else {
                view?.refreshArticles(ArrayList())
            }

        }

        override fun failed(code: Int, log: String) {
            view?.error(log)
        }

    }

    val loadMoreDataCallBack2 = object : DataCallback<ArticleListResponse> {
        override fun succeed(data: ArticleListResponse) {
            if (data.result != null && data.result!!.size > 0) {
                nextPage++
                view?.displayMoreArticles(data.result ?: ArrayList())
            } else {
                view?.displayMoreArticles(ArrayList())
            }
        }

        override fun failed(code: Int, log: String) {
            view?.error(log)
        }
    }


    var nextPage = 1

    val pageSize = 10

    var totalPage = 1


    override fun refreshArticles() {
        ArticleDataRepository.getLatestArticles(
            1, pageSize,
            refreshDataCallBack2
        )
    }

    @CallSuper
    override fun loadMoreArticle(loadMoreAction: (() -> Unit)?) {

        if (loadMoreAction != null) {
            if (canLoadMore()) {
                loadMoreAction.invoke()
            }
        } else {
            if (canLoadMore()) {
                ArticleDataRepository.getLatestArticles(
                    nextPage,
                    10,
                    loadMoreDataCallBack2
                )
            }

        }
    }

    private fun canLoadMore(): Boolean {
        return if (nextPage > totalPage) {
            view?.displayMoreArticles(ArrayList())
            false
        } else {
            true
        }
    }


    override fun destroy() {
        super.destroy()
        view = null
    }


}