package com.lfork.blogsystem.userinfo

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfork.blogsystem.R
import com.lfork.blogsystem.base.viewmodel.Navigator
import com.lfork.blogsystem.databinding.UserInfoFragBinding
import com.lfork.blogsystem.userinfoedit.UserInfoEditActivity
import com.lfork.blogsystem.utils.ToastUtil
import com.lfork.blogsystem.utils.startActivity
import kotlinx.android.synthetic.main.user_info_frag.view.*

class UserInfoFragment : Fragment(),Navigator {
    override fun showTips(msg: String?) {
        ToastUtil.showShort(context,msg)
    }

    companion object {
        fun newInstance() = UserInfoFragment()
    }

    private lateinit var binding: UserInfoFragBinding

    private lateinit var viewModel: UserInfoViewModel

    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (root == null) {
            root = inflater.inflate(R.layout.user_info_frag, container, false)
            viewModel = ViewModelProviders.of(this).get(UserInfoViewModel::class.java)
            viewModel.registerNavigator(this)
            binding = UserInfoFragBinding.bind(root!!)
            binding.viewModel = viewModel
            registerBtnListener(root!!)
        }
        return root as View
    }

    private fun registerBtnListener(root: View) {
        root.btn_edit.setOnClickListener { activity?.startActivity<UserInfoEditActivity>() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserInfoViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshUserInfo()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unregisterNavigator()
    }



}
