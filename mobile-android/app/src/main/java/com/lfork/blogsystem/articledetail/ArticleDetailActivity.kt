package com.lfork.blogsystem.articledetail

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lfork.blogsystem.R
import com.lfork.blogsystem.databinding.ArticleDetailActBinding
import com.lfork.blogsystem.main.MainActivity
import com.lfork.blogsystem.main.home.HomeFragment
import kotlinx.android.synthetic.main.article_detail_act.*

class ArticleDetailActivity : AppCompatActivity() {

    var binding: ArticleDetailActBinding? = null
    var viewModel: ArticleDetailViewModel? = null

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
