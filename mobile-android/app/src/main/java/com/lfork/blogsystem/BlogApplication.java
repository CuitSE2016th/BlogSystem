package com.lfork.blogsystem;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;


import com.lfork.blogsystem.base.thread.MyThreadFactory;
import com.lfork.blogsystem.utils.Config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 98620
 * @date 2018/11/16
 */

public class BlogApplication extends Application {

    public final static String APP_SHARED_PREF = "application_shared_pref";

    public static String token = "null";

    private static ExecutorService executorService;

    /**
     * 这里因为是application context 所以就没有内存泄漏的问题，
     * 因为application的生命周期是最长的
     * application是最后被jvm释放掉的
     */
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initDataBase();
        initThreadPool();
    }

    public void initDataBase() {
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        BlogApplication.context = context;
    }

    public void initThreadPool() {
        ThreadFactory namedThreadFactory = new MyThreadFactory("UI辅助线程池");
        executorService = new ThreadPoolExecutor(Config.BASE_THREAD_POOL_SIZE, Config.BASE_THREAD_POOL_SIZE * 2, 0L, TimeUnit.MICROSECONDS, new LinkedBlockingDeque<Runnable>(), namedThreadFactory);
    }

    public static ExecutorService getAppFixedThreadPool() {
        return executorService;
    }

    public static ExecutorService getDefaultThreadPool() {
        return executorService;
    }

    public static void executeThreadWithThreadPool(Runnable r) {
        executorService.execute(r);
    }
}
