package com.lfork.blogsystem.base.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.support.annotation.CallSuper;

import com.lfork.blogsystem.base.viewmodel.Navigator;

/**
 * Created by 98620 on 2018/3/19.
 */

public abstract class BaseViewModel extends BaseObservable {

    public final ObservableBoolean dataIsLoading = new ObservableBoolean(true);

    public final ObservableBoolean dataIsEmpty = new ObservableBoolean(false);

    private Context context;


    public void start() {
    }

    /**
     * 释放掉一些东西，防止内存泄漏
     */
    @CallSuper
    public void onDestroy() {
    }

    public BaseViewModel() {
    }

    public BaseViewModel(Context context) {
        this.context = context.getApplicationContext();     //force to use application context, avoid potential memory leak
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    //    public abstract void showTips();     大部分viewModel应该都有提示的操作：Toast,SnackBar,自定义提示等等
    public void showTips(String msg) {
    }

}
