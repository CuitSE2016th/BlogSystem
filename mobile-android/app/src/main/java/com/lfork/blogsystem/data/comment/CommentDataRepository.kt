package com.lfork.blogsystem.data.comment

import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.comment.remote.CommentRemoteDataSource

object CommentDataRepository:CommentDataSource {
    private val remoteDataSource:CommentRemoteDataSource= CommentRemoteDataSource()

    override fun addComment(token: String, comment:Comment, callback: DataCallback<Comment>) {
        remoteDataSource.addComment(token, comment, callback)
    }

    override fun deleteComment(token: String, cid: String, callback: DataCallback<String>) {
        remoteDataSource.deleteComment(token, cid, callback)
    }

    override fun addSubComment(
        token: String,
        comment:Comment,
        callback: DataCallback<Comment>
    ) {
      remoteDataSource.addSubComment(token, comment, callback)
    }



    override fun getComments(pageNumber:Int,pageSize:Int,articleId: String, callback: DataCallback<CommentListResponse>) {
        remoteDataSource.getComments(pageNumber, pageSize, articleId, callback)
    }



}