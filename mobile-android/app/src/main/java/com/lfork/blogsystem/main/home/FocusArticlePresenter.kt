package com.lfork.blogsystem.main.home

import com.lfork.blogsystem.BlogApplication.Companion.token
import com.lfork.blogsystem.common.mvp.ArticleContract
import com.lfork.blogsystem.common.mvp.ArticlePresenter
import com.lfork.blogsystem.data.article.ArticleDataRepository


class FocusArticlePresenter(private var view: ArticleContract.View?) : ArticlePresenter(view) {
    override fun refreshArticles() {
        ArticleDataRepository.getFollowingArticles(token!!,1, 10, refreshDataCallBack2)
    }


    override fun loadMoreArticle(loadMoreAction: (() -> Unit)?) {
        super.loadMoreArticle {  ArticleDataRepository.getFollowingArticles(
            token!!,
            nextPage,
            10,
            loadMoreDataCallBack2)}
    }

}
