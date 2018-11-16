package com.lfork.blogsystem.data.common.network

import com.lfork.blogsystem.data.common.DataCallback


/**
 * 对服务器返回结果的进行JSON封装处理
 * Created by 98620 on 2018/9/12.
 * @author 98620
 */
class Result<T> {

    var code: Int = 0

    var message: String? = null

    var data: T? = null

    companion object {
        private const val RESPONSE_SUCCEED = 200

        private const val RESPONSE_FAILED = 100

        const val RESPONSE_UNKNOWN_ERROR = -1

        /**
         * 操作成功 就一个code 比如就是100， 操作失败的话就返回各种code
         */
        fun <T> deal(result: Result<T>?, callback: DataCallback<T>) {
            if (result != null) {
                if (result.code == RESPONSE_SUCCEED) {
                    if (result.data != null) {
                        callback.succeed(result.data!!)
                    }
                } else {
                    callback.failed(result.code, result.message?:"unknown error")
                }
            } else {
                callback.failed(-1, "unknown error")
            }
        }
    }


}
