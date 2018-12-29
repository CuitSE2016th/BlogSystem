package com.lfork.blogsystem.data.article

import com.lfork.blogsystem.base.network.Result
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.user.User
import java.io.File

/**
 *
 * Created by 98620 on 2018/12/11.
 */
interface ArticleDataSource {
    //    fun star()
//
//    fun unStar()
//
//    fun like()
//
//    fun unlike()
//
//    fun search()
//
//    fun getArticle()
//


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
    fun uploadArticleImages(token: String, image: File, callback: DataCallback<String>)

    fun uploadArticleImages(token: String, image: File): Result<String>?
//
//    fun deleteArticle()//architecture

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
        callback: DataCallback<ArticleResponse>
    )

    /**
     * Test接口
     */
    fun getLatestArticles(
        callback: DataCallback<List<Article>>
    )
}