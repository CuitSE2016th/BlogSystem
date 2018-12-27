package com.lfork.blogsystem.starsandlikes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfork.blogsystem.R
import com.lfork.blogsystem.common.fragment.ArticlesFragment

class LikeFragment :  ArticlesFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (root == null) {
            root = inflater.inflate(R.layout.like_frag, container, false)
            super.onCreateView(inflater, container, savedInstanceState)
        }
        // Inflate the layout for this fragment
        return root
    }

}
