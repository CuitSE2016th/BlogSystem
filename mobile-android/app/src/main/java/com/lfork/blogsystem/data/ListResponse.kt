package com.lfork.blogsystem.data

import com.lfork.blogsystem.data.article.Article

abstract class ListResponse(
    var beforePage: Int? = null,
    var currentPage: Int? = null,
    var nextPage: Int? = null,
    var pageCount: Int? = null,
    var pageSize: Int? = null,
    var recordCount: Int? = null
)