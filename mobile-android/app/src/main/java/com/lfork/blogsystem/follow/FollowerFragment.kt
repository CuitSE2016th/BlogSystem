package com.lfork.blogsystem.follow

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfork.blogsystem.R
import kotlinx.android.synthetic.main.follower_frag.view.*

class FollowerFragment : Fragment() {

    companion object {
        fun newInstance() = FollowerFragment()
    }

    private lateinit var viewModel: FollowerViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        val root = inflater.inflate(R.layout.follower_frag, container, false)
        root.recycle_followers.layoutManager = LinearLayoutManager(context)
        root.recycle_followers.adapter = FollowAdapter()
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FollowerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
