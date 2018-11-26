package com.lfork.blogsystem.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

/**
 *
 * @author 98620
 * @date 2018/4/14
 */


public class ToastUtil {

    private final static String MAIN_THREAD = "main";

    public static void showLong(Context context, String content) {
        if (!MAIN_THREAD.equals(Thread.currentThread().getName())) {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
        }
        Toast.makeText(context, content, Toast.LENGTH_LONG).show();
    }

    public static void showShort(Context context, String content) {
        //不能在非主线程里面直接Toast   Can't create handler inside thread that has not called Looper.prepare()
        if (!MAIN_THREAD.equals(Thread.currentThread().getName())) {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
        }
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
