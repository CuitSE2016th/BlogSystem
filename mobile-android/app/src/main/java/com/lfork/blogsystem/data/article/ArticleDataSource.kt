package com.lfork.blogsystem.data.article

/**
 *
 * Created by 98620 on 2018/12/11.
 */
interface ArticleDataSource {
    fun star()

    fun unStar()

    fun like()

    fun unlike()

    fun search()

    fun getThemes()

    fun getArticle()

    fun getLatestArticles()

    fun getFollowingsArticle()

    fun getHeadlines()

    fun getThemesArticles()

    fun publishOrEditArticle()

    fun uploadArticleImages()

    fun deleteArticle()
}