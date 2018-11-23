package com.lfork.blogsystem

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity

/**
 *
 * Created by 98620 on 2018/11/10.
 */
@SuppressLint("Registered")
abstract class BaseActivity: AppCompatActivity() {

    /**
     * 内联函数的类型参数能够被实化。
     * 简单的启动一个activity
     */
    inline fun <reified T:Activity> startActivity(){
        val intent = Intent(this,T::class.java)
        startActivity(intent)
    }
}