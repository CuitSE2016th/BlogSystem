package com.lfork.blogsystem.articledetail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.BlogApplication.Companion.isSignIn
import com.lfork.blogsystem.R
import com.lfork.blogsystem.base.communication.LiveDataBus
import com.lfork.blogsystem.base.widget.listener.BottomListener
import com.lfork.blogsystem.data.article.ArticleDetailResponse
import com.lfork.blogsystem.data.comment.Comment
import com.lfork.blogsystem.data.user.UserDataRepository
import com.lfork.blogsystem.databinding.ArticleDetailActBinding
import com.lfork.blogsystem.login.LoginActivity.Companion.signInFirst
import com.lfork.blogsystem.utils.ShareUtil
import com.lfork.blogsystem.utils.ToastUtil
import com.lfork.blogsystem.utils.hideKeyboard
import kotlinx.android.synthetic.main.article_detail_act.*
import kotlinx.android.synthetic.main.article_detail_navigation_btns.*


class ArticleDetailActivity : AppCompatActivity(), ArticleContentNavigator {


    override fun showTips(msg: String) {

        ToastUtil.showLong(this, msg)
        if (msg == "Deleted"){
            LiveDataBus.get().with("article_deleted").value = "Deleted";
            finish()
        }
    }

    var binding: ArticleDetailActBinding? = null
    var viewModel: ArticleDetailViewModel? = null

    lateinit var mShowAction: TranslateAnimation
    lateinit var mHiddenAction: TranslateAnimation

    var bottomScrollListener:BottomListener?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val articleId = intent.getStringExtra("article_id")

        if (articleId == null) {
            ToastUtil.showLong(BlogApplication.context, "Article id cannot be null!!")
            finish()
            return
        }

        viewModel = ArticleDetailViewModel(articleId)
        binding = DataBindingUtil.setContentView(this, R.layout.article_detail_act);
        binding?.viewModel = viewModel


        val fragment = CommentFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.article_comment_container, fragment)
            .show(fragment)
            .commit()

        bottomScrollListener = fragment


        scroller.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > oldScrollY) {
            }
            if (scrollY < oldScrollY) {
            }

            if (scrollY == 0) {
            }

            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                bottomScrollListener?.onScrollToBottom()
            }
        })

        setupButtonClickListener()
        setupWebView()
    }

    private fun setupButtonClickListener() {
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
        mHiddenAction.duration = 500


        btn_comment.setOnClickListener {

            if (isSignIn) {
                navigation_layout.visibility = View.GONE
//            navigation_layout.startAnimation(mHiddenAction)
                editor_layout.visibility = View.VISIBLE
                editor_layout.startAnimation(mShowAction);

            } else {
                signInFirst(this)
            }
        }

        btn_delete.setOnClickListener {
            openDeleteDialog()
        }

        btn_publish_comment.setOnClickListener {
            if (!TextUtils.isEmpty(editText_comment_content.text)) {
                val c = Comment()
                c.createTime = System.currentTimeMillis().toString()
                c.userId = UserDataRepository.userCache.id
                c.username = UserDataRepository.userCache.getUsername()
                c.portrait = UserDataRepository.userCache.headPortrait
                c.content = editText_comment_content.text.toString()
                c.articleId = viewModel?.articleId
                viewModel?.addComment(c)
                editor_layout.visibility = View.GONE
                navigation_layout.visibility = View.VISIBLE
                hideKeyboard()
            } else {
                ToastUtil.showLong(this@ArticleDetailActivity, "Content cannot be empty!")
            }
        }

        btn_star.setOnClickListener {

            if (isSignIn) {
                viewModel?.starOrUnStarArticle()
            } else {
                signInFirst(this)
            }

        }

        btn_like.setOnClickListener {
            if (isSignIn) {
                viewModel?.likeOrUnlikeArticle()
            } else {
                signInFirst(this)
            }
        }

        btn_share.setOnClickListener {
            ShareUtil.shareTextBySystem(
                this@ArticleDetailActivity,
                viewModel?.title?.get() ?: "Article is null",
                "Article Share"
            )
        }

        btn_follow.setOnClickListener {
            if (isSignIn) {
                viewModel?.followOrUnFollowAuthor()
            } else {
                signInFirst(this)
            }
        }
    }

    private fun openDeleteDialog() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        val builder = AlertDialog.Builder(this)

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setTitle("Confirmation")
        builder.setMessage("Do you really want to delete it?")
//
//        val inputView =TextView(context)
//        builder.setView(inputView)

//        inputView.setText(viewModel.username.get())

        // Add the buttons
        builder.setPositiveButton(R.string.ok) { _, id ->
            viewModel?.deleteArticle()
        }
        //do nothing
        builder.setNegativeButton(R.string.cancel) { dialog, id -> }
        // 3. Get the AlertDialog from create()
        val dialog = builder.create()
        dialog.show()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        article_main_container.settings.javaScriptEnabled = true
        article_main_container.webViewClient = ContentWebClient();
        article_main_container.addJavascriptInterface(
            JavaScriptInterface(this),
            "imagelistner"
        );//这个是给图片设置点击监听的，如果你项目需要webview中图片，点击查看大图功能，可以这么添加
    }

    override fun showContent(htmlContent: ArticleDetailResponse) {
        runOnUiThread {
            title
            article_main_container.loadData(

                htmlContent.content,
                "text/html", "UTF-8"
            )
        }

    }

    override fun onBackPressed() {
        if (editor_layout.visibility == View.VISIBLE) {
            editor_layout.visibility = View.GONE

            navigation_layout.visibility = View.VISIBLE
//            navigation_layout.startAnimation(mShowAction)


        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.navigator = null
    }

    override fun onResume() {
        super.onResume()
        viewModel?.navigator = this
        viewModel?.start()
        //viewModel 就刷新 文章 数据 和作者数据
        //滑到下面的时候就刷新评论数据
    }

    companion object {
        fun openArticleDetail(context: Context, articleId: String) {
            val intent = Intent(context, ArticleDetailActivity::class.java)
            intent.putExtra("article_id", articleId)
            context.startActivity(intent)
        }
    }

}
