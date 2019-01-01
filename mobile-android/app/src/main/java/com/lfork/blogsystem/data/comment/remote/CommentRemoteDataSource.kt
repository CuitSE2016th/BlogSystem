package com.lfork.blogsystem.data.comment.remote

import com.lfork.blogsystem.base.network.HTTPCallBack
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.comment.Comment
import com.lfork.blogsystem.data.comment.CommentDataSource
import com.lfork.blogsystem.data.comment.CommentListResponse

/**
 *
 * @author 98620
 * @date 2018/12/15
 */
class CommentRemoteDataSource : CommentDataSource {
    override fun addComment(
        token: String,
        comment:Comment,
        callback: DataCallback<Comment>
    ) {
        val tempCallBack = object : DataCallback<Comment> {
            override fun succeed(data: Comment) {
                comment.id = data.id
                comment.createTime = data.createTime
                callback.succeed(comment)
            }

            override fun failed(code: Int, log: String) {
                callback.failed(code, log)
            }
        }

        api.publishComment(token, comment.articleId!!, comment.content).enqueue(HTTPCallBack(tempCallBack))
    }

    override fun deleteComment(token: String, cid: String, callback: DataCallback<String>) {
        api.deleteArticle(token, cid).enqueue(HTTPCallBack(callback))
    }

    override fun addSubComment(
        token: String,
        comment:Comment,
        callback: DataCallback<Comment>
    ) {
        val tempCallBack = object : DataCallback<String> {
            override fun succeed(data: String) {
                comment.id = data
                callback.succeed(comment)
            }

            override fun failed(code: Int, log: String) {
                callback.failed(code, log)
            }
        }

        api.replyComment(token, comment.parentId!!, comment.content).enqueue(HTTPCallBack(tempCallBack))
    }

    override fun getComments(
        pageNumber: Int,
        pageSize: Int,
        articleId: String,
        callback: DataCallback<CommentListResponse>
    ) {
        api.getComments(pageNumber, pageSize, articleId).enqueue(HTTPCallBack(callback))
    }

    private val api: CommentApi = CommentApi.create()


}
