package com.lfork.blogsystem.data.article

import com.lfork.blogsystem.base.network.Result
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.article.remote.ArticleRemoteDataSource
import java.io.File

/**
 *
 * Created by 98620 on 2018/12/11.
 */
object ArticleDataRepository : ArticleDataSource {
    override fun getLatestArticles(
        pageNumber: Int,
        pageSize: Int,
        callback: DataCallback<ArticleResponse>
    ) {
        remoteDataSource.getLatestArticles(pageNumber, pageSize, callback)
    }

    override fun uploadArticleImages(token: String, image: File, callback: DataCallback<String>) {
       remoteDataSource.uploadArticleImages(token, image, callback)
    }

    override fun uploadArticleImages(token: String, image: File) =
        remoteDataSource.uploadArticleImages(token, image)

    override fun publishOrEditArticle(
        token: String,
        title: String,
        content: String,
        callback: DataCallback<String>
    ) {
        remoteDataSource.publishOrEditArticle(token, title, content, callback)
    }

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