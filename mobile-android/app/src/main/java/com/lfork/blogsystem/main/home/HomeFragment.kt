package com.lfork.blogsystem.main.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lfork.blogsystem.R
import com.lfork.blogsystem.articleedit.ArticleEditorActivity
import com.lfork.blogsystem.utils.startActivity
import kotlinx.android.synthetic.main.main_home_frag.view.*

class HomeFragment : Fragment() {


    private lateinit var viewModel: HomeViewModel

    private var root:View?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (root == null){
            root = inflater.inflate(R.layout.main_home_frag, container, false)
            root!!.btn_edit.setOnClickListener { context?.startActivity<ArticleEditorActivity>() }
        }

        return root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
