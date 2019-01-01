package com.lfork.blogsystem.data.article

import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.article.remote.ArticleRemoteDataSource
import java.io.File

/**
 *
 * Created by 98620 on 2018/12/11.2
 */
object ArticleDataRepository : ArticleDataSource {
    override fun starArticle(token: String, articleId: String, callback: DataCallback<String>) {
        remoteDataSource.starArticle(token, articleId, callback)
    }

    override fun likeArticle(token: String, articleId: String, callback: DataCallback<String>) {
        remoteDataSource.likeArticle(token, articleId, callback)
    }

    override fun deleteArticle(token: String, articleId: String, callback: DataCallback<String>) {
        remoteDataSource.deleteArticle(token, articleId, callback)
    }

    override fun getArticle(articleId: String, callback: DataCallback<ArticleDetailResponse>) {
        remoteDataSource.getArticle(articleId, callback)
    }

    override fun getMyArticles(
        token: String,
        pageNumber: Int,
        pageSize: Int,
        callback: DataCallback<ArticleListResponse>
    ) {
        remoteDataSource.getMyArticles(token, pageNumber, pageSize, callback)
    }

    override fun getLatestArticles(
        pageNumber: Int,
        pageSize: Int,
        callback: DataCallback<ArticleListResponse>
    ) {
        remoteDataSource.getLatestArticles(pageNumber, pageSize, callback)
    }

    override fun uploadArticleImages(
        token: String,
        image: File,
        callback: DataCallback<ArrayList<String>>
    ) {
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