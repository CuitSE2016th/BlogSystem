package com.lfork.blogsystem

import android.util.Log
import com.lfork.blogsystem.data.common.DataCallback
import com.lfork.blogsystem.data.user.User
import com.lfork.blogsystem.data.user.UserDataRepository
import org.junit.Test

/**
 *
 * Created by 98620 on 2018/11/15.
 */
class URLTest {

    @Test
    fun getVerifyCode() {


        println("enene??1")
        UserDataRepository.getVerifyCode("18628904485", object : DataCallback<String> {
            override fun succeed(data: String) {
                Log.d("getVerifyCodeTest", data)
                println("enene??1")
            }

            override fun failed(log: String) {
                Log.d("getVerifyCodeTest", log)
            }
        })
    }

    @Test
    fun getUserInfo() {

        println("enene??1")
        UserDataRepository.getUserInfo(object :DataCallback<List<User>>{
            override fun succeed(data: List<User>) {
                println("enene??2" + data.toString())
            }

            override fun failed(log: String) {
                println("enene??3")
            }
        })
    }
}