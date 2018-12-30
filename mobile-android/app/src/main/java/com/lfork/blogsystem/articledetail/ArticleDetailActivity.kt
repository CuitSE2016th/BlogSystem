package com.lfork.blogsystem.articledetail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.BlogApplication.Companion.isSignIn
import com.lfork.blogsystem.R
import com.lfork.blogsystem.data.article.ArticleResponse
import com.lfork.blogsystem.data.comment.Comment
import com.lfork.blogsystem.databinding.ArticleDetailActBinding
import com.lfork.blogsystem.login.LoginActivity.Companion.signInFirst
import com.lfork.blogsystem.utils.ShareUtil
import com.lfork.blogsystem.utils.ToastUtil
import kotlinx.android.synthetic.main.article_detail_act.*
import kotlinx.android.synthetic.main.article_detail_navigation_btns.*


class ArticleDetailActivity : AppCompatActivity(),ArticleContentNavigator {
    override fun showTips(msg: String) {
        runOnUiThread { ToastUtil.showLong(this,msg) }
    }



    var binding: ArticleDetailActBinding? = null
    var viewModel: ArticleDetailViewModel? = null

    lateinit var mShowAction:TranslateAnimation
    lateinit var mHiddenAction:TranslateAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val articleId = intent.getStringExtra("article_id")

        if (articleId == null){
            ToastUtil.showLong(BlogApplication.context, "Article id cannot be null!!")
            finish()
            return
        }

        viewModel =ArticleDetailViewModel(articleId)
        binding = DataBindingUtil.setContentView(this, R.layout.article_detail_act);
        binding?.viewModel = viewModel



        val fragment = CommentFragment()
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


        btn_comment.setOnClickListener {

            if (isSignIn ){
                navigation_layout.visibility = View.GONE
//            navigation_layout.startAnimation(mHiddenAction)
                editor_layout.visibility = View.VISIBLE
                editor_layout.startAnimation(mShowAction);

            } else{
                signInFirst(this)
            }
        }

        btn_publish_comment.setOnClickListener {
            if (!TextUtils.isEmpty(editText_comment_content.text)){
                val c = Comment()
                c.userId = "58059810465"
                c.content = editText_comment_content.text.toString()
                viewModel?.addComment(c)
                editor_layout.visibility = View.GONE
                navigation_layout.visibility = View.VISIBLE

                val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)

            }  else{
                ToastUtil.showLong(this@ArticleDetailActivity,"Content cannot be empty!")
            }

        }

        btn_star.setOnClickListener {

            if (isSignIn ){
            } else{
                signInFirst(this)
            }

        }

        btn_like.setOnClickListener {
            if (isSignIn ){
            } else{
                signInFirst(this)
            }

        }

        btn_share.setOnClickListener {
            ShareUtil.shareTextBySystem(this@ArticleDetailActivity, viewModel?.title?.get()?:"Article is null","Article Share" )
        }

        btn_follow.setOnClickListener{
            if (isSignIn ){
            } else{
                signInFirst(this)
            }
        }

        setupWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView(){
        article_main_container.settings.javaScriptEnabled = true
        article_main_container.webViewClient = ContentWebClient();
        article_main_container.addJavascriptInterface(JavaScriptInterface(this), "imagelistner");//这个是给图片设置点击监听的，如果你项目需要webview中图片，点击查看大图功能，可以这么添加
    }

    override fun showContent(htmlContent:ArticleResponse){
        runOnUiThread {
            title
            article_main_container.loadData(

            htmlContent.content,
            "text/html", "UTF-8"
        ) }

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

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.navigator = null
    }

    override fun onResume() {
        super.onResume()
        viewModel?.navigator =this
        viewModel?.start()
        //viewModel 就刷新 文章 数据 和作者数据
        //滑到下面的时候就刷新评论数据
    }

    companion object {
        fun openArticleDetail(context: Context, articleId:String) {
            val intent = Intent(context, ArticleDetailActivity::class.java)
            intent.putExtra("article_id", articleId)
            context.startActivity(intent)
        }
    }

}
