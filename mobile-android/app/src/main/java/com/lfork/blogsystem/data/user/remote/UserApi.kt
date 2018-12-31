package com.lfork.blogsystem.data.user.remote

import com.lfork.blogsystem.base.network.HttpService
import com.lfork.blogsystem.base.network.Result
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
    @POST("user/login")
    fun login(
        @Field("identity") account: String,
        @Field("password") password: String
    ): Call<Result<User>>


    /**
     * 用户注册
     *
     * @return 指定请求数据的Call对象
     */
    @FormUrlEncoded
    @POST("/user/register")
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
    @POST("code")
    fun getVerificationCode(
        @Field("emailOrPhone") account: String
    ): Call<Result<String>>


//
    /**
     * 更新用户头像 单文件上传实例
     * Part的普通键值对需要用RequestBody来写
     * RequestBody.create(null, studentId)
     * @param fileBody  .
     * @param studentId .
     * @return .
     */
    @Multipart
    @POST("user/uploadPortrait")
    fun uploadPortrait(
        @Part("identity") identity: RequestBody,
        @Header("token") token: String,
        @Part fileBody: MultipartBody.Part
    ): Call<Result<User>>

    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("user/updateUserInfo")
    fun updateUserInfo(
        @Field("identity") identity: String,
        @Field("token") token: String,
        @Field("nickname") nickname: String,
        @Field("sex") sex: String?
    ): Call<Result<User>>


    /**
     * 根据Id获取用户的完整信息
     *
     * @param userId .
     * @return .
     */
    @GET("user/getUserInfo")
    fun getUserInfo(
        @Query("identity") account: String,
        @Query("token") token: String
    ): Call<Result<User>>

    companion object {
        fun create(): UserApi {
            val retrofit = HttpService.getRetrofitInstance()
            return retrofit.create(UserApi::class.java)
        }
    }
}