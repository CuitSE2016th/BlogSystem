package com.lfork.blogsystem.articledetail

import com.lfork.blogsystem.base.viewmodel.Navigator
import com.lfork.blogsystem.data.comment.Comment

interface CommentNavigator:Navigator {

    fun refreshComments(comments:ArrayList<Comment>)

    fun addComment(c: Comment)

    fun addSubComment(parent: Comment, child: Comment)

    fun addComments(comments: ArrayList<Comment>)

    fun deleteComment(c:Comment)
}