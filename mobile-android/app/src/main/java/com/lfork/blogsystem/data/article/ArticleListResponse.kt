package com.lfork.blogsystem.data.article

import com.lfork.blogsystem.data.ListResponse
import com.lfork.blogsystem.data.article.Article

data class ArticleListResponse(
    var result: ArrayList<Article>? = null
) : ListResponse()