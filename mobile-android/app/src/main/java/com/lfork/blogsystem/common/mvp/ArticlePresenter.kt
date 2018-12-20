package com.lfork.blogsystem.common.mvp

import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.article.Article
import com.lfork.blogsystem.data.article.ArticleDataRepository
import com.lfork.blogsystem.data.user.UserDataRepository

/**
 *
 * Created by 98620 on 2018/12/15.
 */
open class ArticlePresenter(private var view: ArticleContract.View?) : BasePresenter(),ArticleContract.Presenter {

    val refreshDataCallBack = object : DataCallback<List<Article>> {
        override fun succeed(data: List<Article>) {
            view?.refreshArticles(data as ArrayList<Article>)
        }

        override fun failed(code: Int, log: String) {
           view?.error(log)
        }

    }

    val loadMoreDataCallBack = object : DataCallback<List<Article>> {
        override fun succeed(data: List<Article>) {
            view?.displayMoreArticles(data as ArrayList<Article>)
        }

        override fun failed(code: Int, log: String) {
            view?.error(log)
        }
    }




    override fun refreshArticles() {
        val cacheUser = UserDataRepository.userCache
        ArticleDataRepository.getLatestArticles(
            refreshDataCallBack
        )
    }



    override fun loadMoreArticle(pageNumber: Int) {

        val cacheUser = UserDataRepository.userCache
        ArticleDataRepository.getLatestArticles(
            loadMoreDataCallBack
        )
    }

    override fun destroy() {
        super.destroy()
        view = null
    }


}