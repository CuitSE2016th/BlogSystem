package com.lfork.blogsystem.main.home

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
        val cacheUser = UserDataRepository.userCache
//        ArticleDataRepository.getUsesArticles(
//            cacheUser.getAccount(),
//            BlogApplication.token!!,
//            refreshDataCallBack
//        )
        ArticleDataRepository.getLatestArticles(1, 10, object : DataCallback<ArticleListResponse> {
            override fun succeed(data: ArticleListResponse) {
                if (data.result != null)
                    view?.refreshArticles(data.result!!)
                else {
                    failed(0,"数据为空")
                }
            }

            override fun failed(code: Int, log: String) {
              view?.error(log)
            }
        })
    }

    override fun loadMoreArticle(pageNumber: Int) {
        val cacheUser = UserDataRepository.userCache
        ArticleDataRepository.loadMoreUsesArticles(
            pageNumber,
            "test",//cacheUser.getAccount(),
          "test",//  BlogApplication.token!!,
            loadMoreDataCallBack
        )
    }
}