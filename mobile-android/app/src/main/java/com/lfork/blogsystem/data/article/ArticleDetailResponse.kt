package com.lfork.blogsystem.data.article

import com.google.gson.annotations.SerializedName
import com.lfork.blogsystem.data.article.Article

data class ArticleDetailResponse(
    var authorId: String? = null,
    var content: String? = null,
    var createTime: String = "",
    var commCount:Int?=null,
    var id: String? = null,
    var status: String? = null,
    var title: String? = null,
    var imageUrl:String?=null,
    var likeCount:Int?=null,
    var starCount:Int?=null,
    var username:String,
    var stared:Boolean?=null,
    @SerializedName("like")
    var liked:Boolean?=null,
    var time:String?=null
)