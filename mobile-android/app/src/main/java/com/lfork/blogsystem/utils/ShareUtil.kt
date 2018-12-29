package com.lfork.blogsystem.utils

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log

/**
 * Created by 98620 on 2018/6/20.
 */
object ShareUtil {
    private val TAG = "ShareUtil"
    fun shareTextBySystem(context: Context?, text: String, tips: String) {
        if (context == null || TextUtils.isEmpty(text) || TextUtils.isEmpty(tips)) {
            Log.d(TAG, "shareTextBySystem: 参数不能为空")
            return
        }
        val intent = Intent()
        intent.setAction(Intent.ACTION_SEND).type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        context.startActivity(Intent.createChooser(intent, tips))
    }
}
