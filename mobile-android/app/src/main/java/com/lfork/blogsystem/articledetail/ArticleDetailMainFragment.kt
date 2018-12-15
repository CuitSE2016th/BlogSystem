package com.lfork.blogsystem.articledetail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfork.blogsystem.R

class ArticleDetailMainFragment : Fragment() {

    companion object {
        fun newInstance() = ArticleDetailMainFragment()
    }

    private lateinit var viewModel: ArticleDetailMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.article_detail_main_frag, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ArticleDetailMainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
