package com.lfork.blogsystem.starsandlikes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfork.blogsystem.R
import com.lfork.blogsystem.common.fragment.ArticlesFragment

class StarFragment : ArticlesFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (root == null) {
            root = inflater.inflate(R.layout.star_frag, container, false)
            super.onCreateView(inflater, container, savedInstanceState)
        }
        return root
    }

}
