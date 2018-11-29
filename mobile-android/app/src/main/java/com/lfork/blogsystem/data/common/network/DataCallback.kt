package com.lfork.blogsystem.data.common.network
/**
 *
 * Created by 98620 on 2018/11/15.
 */
interface DataCallback<T> {


    fun succeed(data:T)

    fun failed(code:Int,log:String)
}