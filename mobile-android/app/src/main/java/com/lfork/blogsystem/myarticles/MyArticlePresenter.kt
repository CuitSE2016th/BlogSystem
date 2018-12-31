package com.lfork.blogsystem.myarticles

import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.common.mvp.ArticleContract
import com.lfork.blogsystem.common.mvp.ArticlePresenter
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.article.ArticleDataRepository
import com.lfork.blogsystem.data.article.ArticleListResponse

/**
 *
 * Created by 98620 on 2018/12/15.
 */
class MyArticlePresenter(private var view: ArticleContract.View?) :
    ArticlePresenter(view) {



    var nextPage = 1

    var pageSize = 10

    override fun refreshArticles() {
        if (isRefreshing){
            return
        }
        isRefreshing = true
        nextPage = 1
        ArticleDataRepository.getMyArticles(
            BlogApplication.token!!,
            nextPage,
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

                    nextPage++
                    view?.refreshArticles(articles)
                    isRefreshing = false
                }

                override fun failed(code: Int, log: String) {
                    view?.error(log)
                    isRefreshing = false
                }
            })
    }

    override fun loadMoreArticle(pageNumber: Int) {
        ArticleDataRepository.getMyArticles(
            BlogApplication.token!!,
            nextPage,
            pageSize,
            object : DataCallback<ArticleListResponse> {
                override fun succeed(data: ArticleListResponse) {
                    val articles = data.result
                    if (articles == null) {
                        failed(0, "数据为空")
                        return
                    }
                    nextPage++
                    view?.displayMoreArticles(articles)
                }

                override fun failed(code: Int, log: String) {
                    view?.error(log)
                }
            })
    }
}