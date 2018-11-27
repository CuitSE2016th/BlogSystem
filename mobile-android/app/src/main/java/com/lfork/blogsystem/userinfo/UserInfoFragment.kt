package com.lfork.blogsystem.userinfo

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfork.blogsystem.R

class UserInfoFragment : Fragment() {

    companion object {
        fun newInstance() = UserInfoFragment()
    }

    private lateinit var viewModel: UserInfoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.user_info_frag, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
