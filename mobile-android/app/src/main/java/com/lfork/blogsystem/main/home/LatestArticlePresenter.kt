package com.lfork.blogsystem.main.home

import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.common.mvp.ArticleContract
import com.lfork.blogsystem.common.mvp.ArticlePresenter
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.article.ArticleDataRepository
import com.lfork.blogsystem.data.article.ArticleListResponse
import com.lfork.blogsystem.data.user.UserDataRepository

/**
 *
 * Created by 98620 on 2018/12/15.
 */
class LatestArticlePresenter(private var view: ArticleContract.View?) :
    ArticlePresenter(view) {

    override fun refreshArticles() {
        ArticleDataRepository.getLatestArticles(1, 10, refreshDataCallBack2)
    }


    override fun loadMoreArticle(loadMoreAction: (() -> Unit)?) {
        super.loadMoreArticle (loadMoreAction)
    }

    override fun destroy() {
        super.destroy()
        view = null
    }
}