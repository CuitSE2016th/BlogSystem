package com.lfork.blogsystem.data.user.remote

import com.lfork.blogsystem.data.common.network.HttpService
import com.lfork.blogsystem.data.common.network.Result
import com.lfork.blogsystem.data.user.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

/**
 *
 * Created by 98620 on 2018/11/15.
 */
interface UserApi {

    /**
     * 用户登录
     *
     * @param account email or phone number
     * @return 指定请求数据的Call对象
     */
    @FormUrlEncoded
    @POST("blog/user_login")
    fun login(
        @Field("emailOrPhone") account: String,
        @Field("password") password: String
    ): Call<Result<User>>


    /**
     * 用户注册
     *
     * @return 指定请求数据的Call对象
     */
    @FormUrlEncoded
    @POST("blog/userRegist")
    fun register(
        @Field("emailOrPhone") account: String,
        @Field("emailOrPhoneCode") verifyCode: String,
        @Field("password") password: String
    ): Call<Result<String>>

    /**
     * 用户注册
     *
     * @return 指定请求数据的Call对象
     */
    @FormUrlEncoded
    @POST("blog/code")
    fun getVerifyCode(
        @Field("emailOrPhone") account: String
    ): Call<Result<String>>

//    /**
//     * 根据Id获取用户的完整信息
//     *
//     * @param userId .
//     * @return .
//     */
//    @GET("blog/user_info")
//    fun getUserInfo(@Query("studentId") userId: String): Call<Result<User>>
//
//
//    /**
//     * 更新用户头像 单文件上传实例
//     * Part的普通键值对需要用RequestBody来写
//     * RequestBody.create(null, studentId)
//     * @param fileBody  .
//     * @param studentId .
//     * @return .
//     */
//    @Multipart
//    @POST("blog/user_imageUpload")
//    fun updatePortrait(
//        @Part fileBody: MultipartBody.Part,
//        @Part("studentId") studentId: RequestBody
//    ): Call<Result<String>>
//
//    /**
//     * @param studentId .
//     * @param username .
//     * @param schoolId .
//     * @param dec .
//     * @param email .
//     * @param phone .
//     * @return .
//     */
//    @FormUrlEncoded
//    @POST("blog/user_save")
//    fun updateUserInfo(
//        @Field("studentId") studentId: Int,
//        @Field("userName") username: String,
//        @Field("userSchool.id") schoolId: String,
//        @Field("userDesc") dec: String,
//        @Field("userEmail") email: String,
//        @Field("userPhone") phone: String
//    ): Call<Result<User>>


    /**
     * 根据Id获取用户的完整信息
     *
     * @param userId .
     * @return .
     */
    @GET("getUserInfo")
    fun getUserInfo(): Call<Result<List<User>>>

    companion object {
        fun create(): UserApi {
            val retrofit = HttpService.getRetrofitInstance()
            return retrofit.create(UserApi::class.java)
        }
    }
}