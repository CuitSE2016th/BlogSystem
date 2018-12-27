package com.lfork.blogsystem.data.article

import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.article.remote.ArticleRemoteDataSource

/**
 *
 * Created by 98620 on 2018/12/11.
 */
object ArticleDataRepository:ArticleDataSource {
    override fun getLatestArticles(callback: DataCallback<List<Article>>) {
        remoteDataSource.getLatestArticles(callback)
    }

    override fun loadMoreUsesArticles(
        pageNumber: Int,
        account: String,
        token: String,
        callback: DataCallback<List<Article>>
    ) {
        remoteDataSource.loadMoreUsesArticles(pageNumber, account, token, callback)
    }

    private val remoteDataSource = ArticleRemoteDataSource()

    override fun getUsesArticles(
        account: String,
        token: String,
        callback: DataCallback<List<Article>>
    ) {
        remoteDataSource.getUsesArticles(account, token, callback)
    }
}