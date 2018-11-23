package com.lfork.blogsystem.main.my

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lfork.blogsystem.R
import com.lfork.blogsystem.login.LoginActivity
import com.lfork.blogsystem.main.MainActivity
import kotlinx.android.synthetic.main.main_my_frag.view.*

class MyFragment : Fragment() {

    private lateinit var mainActivity:MainActivity

    companion object {
        fun newInstance() = MyFragment()
    }

    private lateinit var viewModel: MyViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.main_my_frag, container, false)
        registerListener(view)

        return view
    }

    private fun registerListener(view:View){
        view.username.setOnClickListener { mainActivity.startActivity<LoginActivity>() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainActivity = activity as MainActivity
        viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
