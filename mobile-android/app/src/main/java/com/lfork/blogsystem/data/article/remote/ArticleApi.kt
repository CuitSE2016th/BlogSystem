package com.lfork.blogsystem.data.article.remote

import com.lfork.blogsystem.base.network.HttpService
import com.lfork.blogsystem.base.network.Result
import com.lfork.blogsystem.data.article.ArticleResponse
import com.lfork.blogsystem.data.user.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
    ): Call<Result<ArticleResponse>>

    @FormUrlEncoded
    @POST("user/article/publish")
    fun publishArticle(
        @Header("token") token: String,
        @Field("title") title: String,
        @Field("content") content: String?
    ): Call<Result<String>>


    @Multipart
    @POST("user/article/upload")
    fun uploadArticleImage(
        @Header("token") token:  String,
        @Part fileBody: MultipartBody.Part  //image
    ): Call<Result<String>>

}