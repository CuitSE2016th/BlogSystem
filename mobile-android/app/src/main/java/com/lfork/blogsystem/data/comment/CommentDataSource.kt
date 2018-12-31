package com.lfork.blogsystem.data.comment

import com.lfork.blogsystem.data.DataCallback

/**
 *
 * Created by 98620 on 2018/12/11.
 */
interface CommentDataSource {
    fun addComment(token:String,articleId:String,content:String,callback:DataCallback<Comment>)

    fun deleteComment(token:String,cid:String,callback:DataCallback<String>)

    fun addSubComment(token:String,pid:String,content:String,callback:DataCallback<Comment>)

    fun getComments(pageNumber:Int,pageSize:Int,articleId:String,callback: DataCallback<CommentListResponse>)
}