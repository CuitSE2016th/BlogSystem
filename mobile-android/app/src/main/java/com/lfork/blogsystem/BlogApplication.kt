package com.lfork.blogsystem

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context


import android.content.SharedPreferences
import com.lfork.blogsystem.base.thread.MyThreadFactory
import com.lfork.blogsystem.data.user.UserDataRepository
import com.lfork.blogsystem.common.Config

import java.util.concurrent.ExecutorService
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * @author 98620
 * @date 2018/11/16
 */

class BlogApplication : Application() {

    companion object {

        val APP_SHARED_PREF = "application_shared_pref"

        @JvmStatic
        var token: String? = null

        @JvmStatic
        var userId: String? = null

        var isSignIn = false

        var appFixedThreadPool: ExecutorService? = null
            private set

        /**
         * 这里因为是application context 所以就没有内存泄漏的问题，
         * 因为application的生命周期是最长的
         * application是最后被jvm释放掉的
         */
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null

        fun doAsyncTask(r: () -> Unit) {
            appFixedThreadPool!!.execute(r)
        }

        lateinit var sp: SharedPreferences

        fun saveNickname(nickname: String) {
            val editor = sp.edit()
            editor.putString("title", nickname)
            editor.apply()
        }


        fun saveEmail(email: String) {
            val editor = sp.edit()
            editor.putString("email", email)
            editor.apply()
        }

        fun savePhone(phone: String) {
            val sp = context!!.getSharedPreferences("user_info", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString("phone", phone)
            editor.apply()
        }


        fun saveToken(token: String) {
            this.token = token
            val sp = context!!.getSharedPreferences("user_info", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString("token", token)
            editor.apply()
        }

        fun saveId(id: String) {
            userId = id
            val sp = context!!.getSharedPreferences("user_info", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString("id", id)
            editor.apply()
        }

        /**
         * log out
         */
        fun clearUserInfo() {
            val sp = context!!.getSharedPreferences("user_info", Context.MODE_PRIVATE)
            sp.edit().clear().apply()
            token = null
            isSignIn = false
            userId = null
        }


    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initDataBase()
        initThreadPool()
        initUserInfo();
    }

    private fun initDataBase() {}

    private fun initThreadPool() {
        val namedThreadFactory = MyThreadFactory("UI辅助线程池")
        appFixedThreadPool = ThreadPoolExecutor(
            Config.BASE_THREAD_POOL_SIZE,
            Config.BASE_THREAD_POOL_SIZE * 2,
            0L,
            TimeUnit.MICROSECONDS,
            LinkedBlockingDeque(),
            namedThreadFactory
        )
    }

    private fun initUserInfo() {
        sp = context!!.getSharedPreferences("user_info", Context.MODE_PRIVATE)
        UserDataRepository.initBasicUserInfo(
            sp.getString("email", null),
            sp.getString("phone", null),
            sp.getString("title", null),
            sp.getString("id", null)
        )
        token = sp.getString("token", null)
        userId =sp.getString("id",null)

        if (token != null) {
            isSignIn = true
        }
    }

    fun clearCache() {
        sp = context!!.getSharedPreferences("user_info", Context.MODE_PRIVATE)
        sp.edit().clear().apply()
    }


}
