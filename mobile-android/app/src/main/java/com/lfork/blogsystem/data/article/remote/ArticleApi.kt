package com.lfork.blogsystem.data.article.remote

import com.lfork.blogsystem.base.network.HttpService
import com.lfork.blogsystem.base.network.Result
import com.lfork.blogsystem.data.article.ArticleListResponse
import com.lfork.blogsystem.data.article.ArticleResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

/**
 *
 * Created by 98620 on 2018/12/15.
 */
interface ArticleApi {


    companion object {
        fun create(): ArticleApi {
            val retrofit = HttpService.getRetrofitInstance()
            return retrofit.create(ArticleApi::class.java)
        }
    }

    @GET("user/article/page")
    fun getLatestArticles(
        @Query("pn") pageNumber: Int,
        @Query("ps") pageSize: Int
    ): Call<Result<ArticleListResponse>>

    @GET("user/article/myself")
    fun getMyArticles(
        @Header("token") token: String,
        @Query("pn") pageNumber: Int,
        @Query("ps") pageSize: Int
    ): Call<Result<ArticleListResponse>>

    @GET("user/article/follow")
    fun getFollowingArticles(
        @Query("pn") pageNumber: Int,
        @Query("ps") pageSize: Int
    ): Call<Result<ArticleListResponse>>

    @GET("user/article/id")
    fun getArticle(
        @Header("token") token: String,
        @Query("aid") articleId: String
    ): Call<Result<ArticleResponse>>

    //http://localhost:8080/user/article/myself

    @FormUrlEncoded
    @POST("user/article/publish")
    fun publishArticle(
        @Header("token") token: String,
        @Field("title") title: String,
        @Field("content") content: String?
    ): Call<Result<String>>


    @FormUrlEncoded
    @POST("user/article/delete")
    fun deleteArticle(
        @Header("token") token: String,
        @Field("aid") articleId: String
        ): Call<Result<String>>


    @FormUrlEncoded
    @POST("user/article/star")
    fun starArticle(
        @Header("token") token: String,
        @Field("aid") articleId: String
    ): Call<Result<String>>

    @FormUrlEncoded
    @POST("user/article/like")
    fun likeArticle(
        @Header("token") token: String,
        @Field("aid") articleId: String
    ): Call<Result<String>>


    @Multipart
    @POST("user/article/upload")
    fun uploadArticleImage(
        @Header("token") token: String,
        @Part fileBody: MultipartBody.Part  //image
    ): Call<Result<ArrayList<String>>>

}