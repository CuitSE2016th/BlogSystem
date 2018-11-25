package com.lfork.blogsystem.main.my

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lfork.blogsystem.R
import com.lfork.blogsystem.databinding.MainMyFragBinding
import com.lfork.blogsystem.login.LoginActivity
import com.lfork.blogsystem.main.MainActivity
import com.lfork.blogsystem.utils.ToastUtil
import com.lfork.blogsystem.utils.startActivity
import kotlinx.android.synthetic.main.main_my_frag.view.*

class MyFragment : Fragment(), MyNavigator {

    private lateinit var mainActivity: MainActivity

    private lateinit var viewDataBinding: MainMyFragBinding

    private lateinit var viewModel: MyViewModel


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.main_my_frag, container, false)

        viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        viewModel.registerNavigator(this)
        viewModel.init(resources.getString(R.string.click_to_sign_in))

        viewDataBinding = MainMyFragBinding.bind(root)
        viewDataBinding.viewmodel = viewModel

        registerListener(root)
        return viewDataBinding.root
    }

    private fun registerListener(view: View) {
        view.username_or_signin.setOnClickListener { mainActivity.startActivity<LoginActivity>() }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainActivity = activity as MainActivity

    }


    override fun onResume() {
        super.onResume()
        viewModel.refreshData()

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unregisterNavigator()
    }

    override fun showTips(msg: String?) {
        activity?.runOnUiThread { ToastUtil.showShort(context, msg) }
    }


}
