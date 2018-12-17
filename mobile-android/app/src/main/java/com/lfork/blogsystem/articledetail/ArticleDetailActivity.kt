package com.lfork.blogsystem.articledetail

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lfork.blogsystem.R

class ArticleDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_detail_act)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.article_main_container, ArticleDetailMainFragment.newInstance())
                .commitNow()
        }
    }

    companion object {
        fun openArticleDetail(context: Context){
            val intent = Intent(context, ArticleDetailActivity::class.java)
            context.startActivity(intent)
        }
    }

}
