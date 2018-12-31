package com.lfork.blogsystem.data.comment.remote

import com.lfork.blogsystem.base.network.HttpService
import com.lfork.blogsystem.base.network.Result
import com.lfork.blogsystem.data.comment.Comment
import com.lfork.blogsystem.data.comment.CommentListResponse
import retrofit2.Call
import retrofit2.http.*

/**
 *
 * Created by 98620 on 2018/12/15.
 */
interface CommentApi {


    companion object {
        fun create(): CommentApi {
            val retrofit = HttpService.getRetrofitInstance()
            return retrofit.create(CommentApi::class.java)
        }
    }

    @GET("user/comment/page")
    fun getComments(
        @Query("pn") pageNumber: Int,
        @Query("ps") pageSize: Int,
        @Query("aid") articleId: String
    ): Call<Result<CommentListResponse>>



    @FormUrlEncoded
    @POST("user/comment/new")
    fun publishComment(
        @Header("token") token: String,
        @Field("aid") articleId: String,
        @Field("content") content: String?
    ): Call<Result<String>>


    @FormUrlEncoded
    @POST("user/comment/reply")
    fun replyComment(
        @Header("token") token: String,
        @Field("pid") commentId: String,
        @Field("content") content: String?
    ): Call<Result<Comment>>


    @FormUrlEncoded
    @POST("user/comment/delete")
    fun deleteArticle(
        @Header("token") token: String,
        @Field("cid") commentId: String
    ): Call<Result<String>>


}