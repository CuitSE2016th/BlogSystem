package com.lfork.blogsystem.data.comment.remote

import com.lfork.blogsystem.RandomTest
import com.lfork.blogsystem.base.network.MyRetrofitCallBack
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
    override fun addComment(token: String, articleId:String,content: String, callback: DataCallback<Comment>) {
        val c = Comment(
            "11",
            "lfork${22 % 2}",
            RandomTest.getRandomImages(),
            null,
            null,
            "2018/12/30",
            null,
            "100",
            "300",
            content
        )

        val tempCallBack =object :DataCallback<String>{
            override fun succeed(data: String) {
                callback.succeed(c)
            }

            override fun failed(code: Int, log: String) {
                callback.failed(code, log)
            }
        }

        api.publishComment(token,articleId, content).enqueue(MyRetrofitCallBack(tempCallBack))
    }

    override fun deleteComment(token: String, cid: String, callback: DataCallback<String>) {
        callback.succeed("Succeed")
    }

    override fun addSubComment(
        token: String,
        pid: String,
        content: String,
        callback: DataCallback<Comment>
    ) {
        val c = Comment(
            "11",
            "lfork${22 % 2}",
            RandomTest.getRandomImages(),
            pid,
            null,
            "2018/12/17",
            "Tom",
            "100",
            "300",
            content
        )

        callback.succeed(c)
    }

    override fun getComments(pageNumber:Int,pageSize:Int,articleId: String, callback: DataCallback<CommentListResponse>) {
        api.getComments(pageNumber, pageSize, articleId).enqueue(MyRetrofitCallBack(callback))
    }

    private val api: CommentApi = CommentApi.create()


}
