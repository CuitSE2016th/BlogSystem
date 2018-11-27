package com.lfork.blogsystem.follow

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfork.blogsystem.R
import kotlinx.android.synthetic.main.following_frag.view.*

class FollowingFragment : Fragment() {

    companion object {
        fun newInstance() = FollowingFragment()
    }

    private lateinit var viewModel: FollowingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val root = inflater.inflate(R.layout.following_frag, container, false)
        root.recycle_following.layoutManager = LinearLayoutManager(context)
        root.recycle_following.adapter = FollowAdapter()

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FollowingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
