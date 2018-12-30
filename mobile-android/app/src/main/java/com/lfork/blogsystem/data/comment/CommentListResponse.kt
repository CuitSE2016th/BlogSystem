package com.lfork.blogsystem.data.comment

import com.lfork.blogsystem.data.ListResponse
import com.lfork.blogsystem.data.article.Article

data class CommentListResponse(
    var result: ArrayList<Comment>? = null
) : ListResponse()