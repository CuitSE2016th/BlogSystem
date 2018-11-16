package com.lfork.blogsystem.data.common.network

import com.lfork.blogsystem.data.common.DataCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *
 * Created by 98620 on 2018/9/12.
 *
 *
 */
class MyRetrofitCallBack<T>(private val generalCallback: DataCallback<T>) : Callback<Result<T>> {
    override fun onFailure(call: Call<Result<T>>, t: Throwable) {
        println("enene??network_security_config.3")
        generalCallback.failed("error：" +t.message)
    }

    override fun onResponse(call: Call<Result<T>>, response: Response<Result<T>>) {
        println("enene??network_security_config.2")
        Result.deal(response.body() as Result<T>, generalCallback)
    }
}