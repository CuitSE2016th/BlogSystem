package com.lfork.blogsystem.myarticles

import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.common.mvp.ArticleContract
import com.lfork.blogsystem.common.mvp.ArticlePresenter
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.article.Article
import com.lfork.blogsystem.data.article.ArticleDataRepository
import com.lfork.blogsystem.data.article.ArticleListResponse
import com.lfork.blogsystem.data.user.UserDataRepository

/**
 *
 * Created by 98620 on 2018/12/15.
 */
class MyArticlePresenter(private var view: ArticleContract.View?) :
    ArticlePresenter(view) {

    var currentPage = 1

    var pageSize = 10

    override fun refreshArticles() {
        currentPage = 1
        ArticleDataRepository.getMyArticles(
            BlogApplication.token!!,
            currentPage,
            pageSize,
            object : DataCallback<ArticleListResponse> {
                override fun succeed(data: ArticleListResponse) {
                    val articles = data.result
                    if (articles == null) {
                        failed(0, "数据为空")
                        return
                    }

                    if (articles.size < 1) {
                        failed(0, "数据为空")
                        return
                    }

                    currentPage++
                    view?.refreshArticles(articles)
                }

                override fun failed(code: Int, log: String) {
                    view?.error(log)
                }
            })
    }

    override fun loadMoreArticle(pageNumber: Int) {
        ArticleDataRepository.getMyArticles(
            BlogApplication.token!!,
            currentPage,
            pageSize,
            object : DataCallback<ArticleListResponse> {
                override fun succeed(data: ArticleListResponse) {
                    val articles = data.result
                    if (articles == null) {
                        failed(0, "数据为空")
                        return
                    }

                    view?.displayMoreArticles(articles)
                }

                override fun failed(code: Int, log: String) {
                    view?.error(log)
                }
            })
    }
}