package com.lfork.blogsystem.data.article

/**
 *
 * Created by 98620 on 2018/12/11.
 */
data class Article(
    var id: String? = null,
    var title: String? = null,
    var createTime: String? = null,
    var imageUrl: String? = null,
    var abstract: String? = null,
    var authorId: String? = null,
    var content: String? = null,
    var status: Int? = null,
    var commCount: Int? = null,
    var likeCount: Int? = null,
    var starCount:Int?=null


    )