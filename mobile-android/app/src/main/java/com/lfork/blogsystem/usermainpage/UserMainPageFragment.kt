package com.lfork.blogsystem.usermainpage

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfork.blogsystem.R

class UserMainPageFragment : Fragment() {

    companion object {
        fun newInstance() = UserMainPageFragment()
    }

    private lateinit var viewModel: UserMainPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.user_main_page_frag, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserMainPageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
