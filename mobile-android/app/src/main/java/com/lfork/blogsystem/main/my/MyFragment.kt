package com.lfork.blogsystem.main.my

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lfork.blogsystem.R
import com.lfork.blogsystem.data.user.UserDataRepository
import com.lfork.blogsystem.databinding.MainMyFragBinding
import com.lfork.blogsystem.follow.FollowActivity
import com.lfork.blogsystem.follow.FollowActivity.Companion.startFollowActivity
import com.lfork.blogsystem.helpfeedback.HelpFeedBackActivity
import com.lfork.blogsystem.login.LoginActivity
import com.lfork.blogsystem.main.MainActivity
import com.lfork.blogsystem.myarticles.MyArticlesActivity
import com.lfork.blogsystem.settings.SettingsActivity
import com.lfork.blogsystem.starsandlikes.StarLikeActivity
import com.lfork.blogsystem.userinfo.UserInfoActivity
import com.lfork.blogsystem.utils.ToastUtil
import com.lfork.blogsystem.utils.startActivity
import kotlinx.android.synthetic.main.main_my_frag.view.*

class MyFragment : Fragment(), MyNavigator, View.OnClickListener {


    private lateinit var mainActivity: MainActivity

    private lateinit var viewDataBinding: MainMyFragBinding

    private lateinit var viewModel: MyViewModel

    private var root: View? = null


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        if (root == null) {

            root = inflater.inflate(R.layout.main_my_frag, container, false)

            viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
            viewModel.registerNavigator(this)
            viewModel.init(resources.getString(R.string.click_to_sign_in))

            viewDataBinding = MainMyFragBinding.bind(root!!)
            viewDataBinding.viewmodel = viewModel

            registerListener(root!!)
        }
        return viewDataBinding.root
    }

    private fun registerListener(view: View) {
        view.username_or_signin.setOnClickListener(this)
        view.followers.setOnClickListener(this)
        view.following.setOnClickListener(this)

        view.item_my_articles.setOnClickListener(this)
        view.item_star_like.setOnClickListener(this)

        view.item_settings.setOnClickListener(this)
        view.item_help_feedback.setOnClickListener(this)
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

    override fun onClick(v: View?) {
        val id = v?.id

        Log.d("onClick", v.toString())
        when (id) {
            R.id.username_or_signin -> {
                if (UserDataRepository.isSignIn) {
                    mainActivity.startActivity<UserInfoActivity>()
                } else {
                    mainActivity.startActivity<LoginActivity>()
                }
            }
            R.id.followers -> startFollowActivity(context!!, FollowActivity.FOLLOWER_FRAG)
            R.id.following -> startFollowActivity(context!!, FollowActivity.FOLLOWING_FRAG)

            R.id.item_my_articles->mainActivity.startActivity<MyArticlesActivity>()
            R.id.item_star_like->mainActivity.startActivity<StarLikeActivity>()

            R.id.item_settings -> mainActivity.startActivity<SettingsActivity>()
            R.id.item_help_feedback->mainActivity.startActivity<HelpFeedBackActivity>()
        }
    }

    override fun showTips(msg: String?) {
        activity?.runOnUiThread { ToastUtil.showShort(context, msg) }
    }


}
