package com.lfork.blogsystem.articledetail

import com.lfork.blogsystem.base.viewmodel.Navigator
import com.lfork.blogsystem.data.comment.Comment

interface CommentNavigator:Navigator {

    fun refreshComments(comments:ArrayList<Comment>)

    fun loadMoreComments(comments:ArrayList<Comment>)

    fun addComment(c: Comment)

    fun addSubComment(position: Int,parent: Comment, child: Comment)

    fun addComments(comments: ArrayList<Comment>)

    fun deleteComment(position: Int,c:Comment)
}