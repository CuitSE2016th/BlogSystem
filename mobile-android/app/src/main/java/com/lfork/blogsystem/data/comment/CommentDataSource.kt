package com.lfork.blogsystem.data.comment

/**
 *
 * Created by 98620 on 2018/12/11.
 */
interface CommentDataSource {
    fun addComment()

    fun deleteComment()

    fun getComments()
}