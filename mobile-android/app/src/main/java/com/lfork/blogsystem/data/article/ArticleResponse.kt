package com.lfork.blogsystem.data.article

import com.lfork.blogsystem.data.article.Article

data class ArticleResponse(
    var beforePage: Int? = null,
    var currentPage: Int? = null,
    var nextPage: Int? = null,
    var pageCount: Int? = null,
    var pageSize: Int? = null,
    var recordCount: Int? = null,
    var result: ArrayList<Article>?=null

)