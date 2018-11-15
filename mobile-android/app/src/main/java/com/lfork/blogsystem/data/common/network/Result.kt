package com.lfork.blogsystem.data.common.network

import com.google.gson.annotations.SerializedName
import com.lfork.blogsystem.data.common.DataCallback


/**
 * 对服务器返回结果的进行JSON封装处理
 * Created by 98620 on 2018/9/12.
 * @author 98620
 */
class Result<T> {

    @SerializedName("id")
    var code: Int = 0

    @SerializedName("msg")
    var message: String? = null

    var data: T? = null

    companion object {
        fun <T>deal(result: Result<T>?, callback: DataCallback<T>) {
            if (result != null) {
                val u = result.data
                if (u != null) {
                    callback.succeed(u)
                } else {
                    callback.failed(result.message?:"error")
                }
            } else {
                callback.failed("error")
            }
        }
    }


}
