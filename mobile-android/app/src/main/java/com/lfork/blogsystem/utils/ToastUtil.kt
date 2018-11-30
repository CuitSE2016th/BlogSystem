package com.lfork.blogsystem.utils

import android.content.Context
import android.os.Looper
import android.util.Log
import android.widget.Toast

/**
 *
 * @author 98620
 * @date 2018/4/14
 */


object ToastUtil {

    private val MAIN_THREAD = "main"

    private val TAG = "ToastUtil"

    fun showLong(context: Context?, content: String?) {
        if (MAIN_THREAD != Thread.currentThread().name) {
            if (Looper.myLooper() == null) {
                Looper.prepare()
            }
        }

        if(context == null) {
            Log.w(TAG, "Context is null")
        }
        Toast.makeText(context, content, Toast.LENGTH_LONG).show()
    }

    fun showShort(context: Context?, content: String?) {
        //不能在非主线程里面直接Toast   Can't create handler inside thread that has not called Looper.prepare()
        if (MAIN_THREAD != Thread.currentThread().name) {
            if (Looper.myLooper() == null) {
                Looper.prepare()
            }
        }
        if(context == null) {
            Log.w(TAG, "Context is null")
        }
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
    }

}
