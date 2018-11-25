package com.lfork.blogsystem

import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.lfork.blogsystem.data.common.DataCallback
import com.lfork.blogsystem.data.user.User
import com.lfork.blogsystem.data.user.UserDataRepository
import org.junit.Test
import org.junit.runner.RunWith

/**
 *
 * Created by 98620 on 2018/11/15.
 */
@RunWith(AndroidJUnit4::class)
class URLTest {

    @Test
    fun getUserInfo() {

//        Log.d("getUserInfoTest","enene??network_security_config")
//        UserDataRepository.getCurrentUserInfo(object :DataCallback<List<User>>{
//            override fun succeed(data: List<User>) {
//                Log.d("getUserInfoTest","enene??2" + data.toString())
//            }
//
//            override fun failed(code: Int,log: String) {
//                Log.d("getUserInfoTest","enene??3")
//            }
//        })
    }

//    @Test
//    fun getVerificationCode() {
//
//
//        println("enene??network_security_config")
//        UserDataRepository.getVerificationCode("18628904485", object : DataCallback<String> {
//            override fun succeed(data: String) {
//                Log.d("getVerifyCodeTest", data)
//                println("enene??network_security_config")
//            }
//
//            override fun failed(log: String) {
//                Log.d("getVerifyCodeTest", log)
//            }
//        })
//    }


}