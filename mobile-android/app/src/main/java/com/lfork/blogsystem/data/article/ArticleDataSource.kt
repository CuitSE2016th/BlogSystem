package com.lfork.blogsystem.data.article

import com.lfork.blogsystem.base.network.Result
import com.lfork.blogsystem.data.DataCallback
import java.io.File

/**
 *
 * Created by 98620 on 2018/12/11.
 */
interface ArticleDataSource {
    fun starArticle(token: String, articleId: String, callback: DataCallback<String>)
    //
    fun unStarArticle(token: String, articleId: String, callback: DataCallback<String>)

    //
    fun likeArticle(token: String, articleId: String, callback: DataCallback<String>)

    //
    fun unLikeArticle(token: String, articleId: String, callback: DataCallback<String>)

    //
//    fun search()
//
    fun getArticle(articleId: String, callback: DataCallback<ArticleDetailResponse>)
//

    fun getMyArticles(
        token: String,
        pageNumber: Int,
        pageSize: Int,
        callback: DataCallback<ArticleListResponse>
    )

    fun getFollowingArticles(
        token: String, pageNumber: Int, pageSize: Int, callback: DataCallback<ArticleListResponse>
    )

    fun getLikedArticles(
        token: String,
        pageNumber: Int,
        pageSize: Int,
        callback: DataCallback<ArticleListResponse>
    )

    fun getStaredArticles(
        token: String,
        pageNumber: Int,
        pageSize: Int,
        callback: DataCallback<ArticleListResponse>
    )


    //
//    fun getFollowingsArticle()
//
//    fun getHeadlines()
//
//    fun getThemes()
//
//    fun getThemesArticles()
//
    fun publishOrEditArticle(
        token: String,
        title: String,
        content: String,
        callback: DataCallback<String>
    )

    ////
    fun uploadArticleImages(token: String, image: File, callback: DataCallback<ArrayList<String>>)

    fun uploadArticleImages(token: String, image: File): Result<ArrayList<String>>?
    //
    fun deleteArticle(
        token: String,
        articleId: String,
        callback: DataCallback<String>
    )//architecture

    fun getUsesArticles(account: String, token: String, callback: DataCallback<List<Article>>)

    fun loadMoreUsesArticles(
        pageNumber: Int,
        account: String,
        token: String,
        callback: DataCallback<List<Article>>
    )

    fun getLatestArticles(
        pageNumber: Int,
        pageSize: Int,
        callback: DataCallback<ArticleListResponse>
    )

    /**
     * Test接口
     */
    fun getLatestArticles(
        callback: DataCallback<List<Article>>
    )
}