package com.lfork.blogsystem.articledetail

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import com.lfork.blogsystem.BlogApplication.Companion.isSignIn
import com.lfork.blogsystem.R
import com.lfork.blogsystem.databinding.ArticleDetailActBinding
import com.lfork.blogsystem.login.LoginActivity
import com.lfork.blogsystem.utils.ToastUtil
import com.lfork.blogsystem.utils.startActivity
import kotlinx.android.synthetic.main.article_detail_act.*
import kotlinx.android.synthetic.main.article_detail_navigation_btns.*


class ArticleDetailActivity : AppCompatActivity() {

    var binding: ArticleDetailActBinding? = null
    var viewModel: ArticleDetailViewModel? = null

    lateinit var mShowAction:TranslateAnimation
    lateinit var mHiddenAction:TranslateAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ArticleDetailViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.article_detail_act);
        binding?.viewModel = viewModel

        article_main_container.loadData(
            viewModel?.htmlTestData?.get(),
            "text/html", "UTF-8"
        )

        val fragment = ArticleCommentFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.article_comment_container, fragment)
            .show(fragment)
            .commit()

        mShowAction = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
            -1.0f, Animation.RELATIVE_TO_SELF, 0.0f
        )
        mShowAction.duration = 500

        mHiddenAction = TranslateAnimation(
            Animation.RELATIVE_TO_SELF,
            0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
            -1.0f
        )
        mHiddenAction.setDuration(500)


        var changed = false
        btn_comment.setOnClickListener {
//            TransitionManager.beginDelayedTransition(navigation_or_inputbox)
//            val constraint = if (changed) constraintSet1 else constraintSet2
//            constraint.applyTo(navigation_or_inputbox)
//            changed = !changed

            if (isSignIn ){
                navigation_layout.visibility = View.GONE
//            navigation_layout.startAnimation(mHiddenAction)

                editor_layout.visibility = View.VISIBLE
                editor_layout.startAnimation(mShowAction);
            } else{
                ToastUtil.showLong(this@ArticleDetailActivity, "Please sign in.")
                this@ArticleDetailActivity.startActivity<LoginActivity>()
            }


        }

    }

    override fun onBackPressed() {
        if (editor_layout.visibility == View.VISIBLE){
            editor_layout.visibility = View.GONE

            navigation_layout.visibility = View.VISIBLE
//            navigation_layout.startAnimation(mShowAction)


        } else {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        //viewModel 就刷新 文章 数据 和作者数据
        //滑到下面的时候就刷新评论数据
    }

    companion object {
        fun openArticleDetail(context: Context) {
            val intent = Intent(context, ArticleDetailActivity::class.java)
            context.startActivity(intent)
        }
    }

}
