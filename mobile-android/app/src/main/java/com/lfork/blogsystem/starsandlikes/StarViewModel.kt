package com.lfork.blogsystem.starsandlikes

import android.arch.lifecycle.ViewModel

class StarViewModel : ViewModel() {
    val mTitleDataList = ArrayList<String>()


    init {
        mTitleDataList.add("Likes")
        mTitleDataList.add("Stars")
    }
}
