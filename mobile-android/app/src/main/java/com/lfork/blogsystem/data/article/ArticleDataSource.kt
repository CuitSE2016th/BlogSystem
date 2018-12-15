package com.lfork.blogsystem.data.article

import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.user.User

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
//    fun getLatestArticles()
//
//    fun getFollowingsArticle()
//
//    fun getHeadlines()
//
//    fun getThemes()
//
//    fun getThemesArticles()
//
//    fun publishOrEditArticle()
//
//    fun uploadArticleImages()
//
//    fun deleteArticle()//architecture

    fun getUsesArticles(account: String, token: String, callback: DataCallback<List<Article>>)

    fun loadMoreUsesArticles(pageNumber:Int,account: String, token: String, callback: DataCallback<List<Article>>)
}