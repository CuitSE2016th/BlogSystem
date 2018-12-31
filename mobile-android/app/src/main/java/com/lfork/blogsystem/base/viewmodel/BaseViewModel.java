package com.lfork.blogsystem.base.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.support.annotation.CallSuper;


/**
 * Created by 98620 on 2018/3/19.
 */

public abstract class BaseViewModel extends ViewModel {

    public final ObservableBoolean dataIsLoading = new ObservableBoolean(true);

    public final ObservableBoolean dataIsEmpty = new ObservableBoolean(false);

    public final ObservableBoolean dataLoadError = new ObservableBoolean(false);
}
