package com.lfork.blogsystem.myarticles

import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.article.Article
import com.lfork.blogsystem.data.article.ArticleDataRepository
import com.lfork.blogsystem.data.user.UserDataRepository

/**
 *
 * Created by 98620 on 2018/12/15.
 */
class MyArticlePresenter( private var view: MyArticleContract.View?) : MyArticleContract.Presenter {

    private val refreshDataCallBack = object : DataCallback<List<Article>> {
        override fun succeed(data: List<Article>) {
            view?.refreshArticles(data as ArrayList<Article>)
        }

        override fun failed(code: Int, log: String) {
           view?.error(log)
        }

    }

    private val loadMoreDataCallBack = object : DataCallback<List<Article>> {
        override fun succeed(data: List<Article>) {
            view?.displayMoreArticles(data as ArrayList<Article>)
        }

        override fun failed(code: Int, log: String) {
            view?.error(log)
        }
    }


    override fun refreshArticles() {
        val cacheUser = UserDataRepository.userCache
        ArticleDataRepository.getUsesArticles(
            cacheUser.getAccount(),
            BlogApplication.token!!,
            refreshDataCallBack
        )
    }

    override fun loadMoreArticle(pageNumber: Int) {
        val cacheUser = UserDataRepository.userCache
        ArticleDataRepository.loadMoreUsesArticles(
            pageNumber,
            cacheUser.getAccount(),
            BlogApplication.token!!,
            loadMoreDataCallBack
        )
    }

    override fun destroy() {
        super.destroy()
        view = null
    }
}