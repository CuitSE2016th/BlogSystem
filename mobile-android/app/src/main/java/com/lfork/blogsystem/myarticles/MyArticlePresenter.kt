package com.lfork.blogsystem.myarticles

import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.common.mvp.ArticleContract
import com.lfork.blogsystem.common.mvp.ArticlePresenter
import com.lfork.blogsystem.data.article.ArticleDataRepository
import com.lfork.blogsystem.data.user.UserDataRepository

/**
 *
 * Created by 98620 on 2018/12/15.
 */
class MyArticlePresenter(private var view: ArticleContract.View?) :
    ArticlePresenter(view) {

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
}