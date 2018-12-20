package com.lfork.blogsystem.main.home

import android.arch.lifecycle.ViewModel;

class HomeViewModel : ViewModel() {
    val mTitleDataList = ArrayList<String>()


    init {
        mTitleDataList.add("Latest")
        mTitleDataList.add("My Focus")
    }

}
