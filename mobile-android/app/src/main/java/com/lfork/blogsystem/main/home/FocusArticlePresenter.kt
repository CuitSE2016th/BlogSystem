package com.lfork.blogsystem.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lfork.blogsystem.R
import com.lfork.blogsystem.common.fragment.ArticlesFragment


class FocusArticlePresenter : ArticlesFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (root == null) {
            root = inflater.inflate(R.layout.main_home_focus_article_inner_frag, container, false)
            super.onCreateView(inflater, container, savedInstanceState)
        }
        // Inflate the layout for this fragment
        return root
    }

}
