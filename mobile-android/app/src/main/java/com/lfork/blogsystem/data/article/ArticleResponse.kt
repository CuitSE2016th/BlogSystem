package com.lfork.blogsystem.data.article

import com.lfork.blogsystem.data.article.Article

data class ArticleResponse(
    var authorId: String? = null,
    var content: String? = null,
    var createTime: String? = null,
    var id: String? = null,
    var status: String? = null,
    var title: String? = null

)