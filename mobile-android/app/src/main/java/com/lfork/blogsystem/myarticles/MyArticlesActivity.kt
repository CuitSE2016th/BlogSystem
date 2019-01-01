package com.lfork.blogsystem.myarticles

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.lfork.blogsystem.R
import com.lfork.blogsystem.base.communication.LiveDataBus
import com.lfork.blogsystem.common.adapter.ArticlesAdapter
import com.lfork.blogsystem.common.mvp.ArticleContract
import com.lfork.blogsystem.data.article.Article
import com.lfork.blogsystem.utils.ToastUtil
import com.lfork.blogsystem.utils.setupToolBar
import kotlinx.android.synthetic.main.my_articles_act.*

class MyArticlesActivity : AppCompatActivity(), ArticleContract.View {

    private fun hideDataIsLoading(){
        data_is_loading.visibility = View.GONE
    }

    private fun showDataIsLoading(){
        data_is_loading.visibility = View.VISIBLE
    }
    override fun refreshArticles(articles: ArrayList<Article>) {
        runOnUiThread {

            adapter.refreshItems(articles)
            refresh_layout.isRefreshing = false
            recycle_articles.visibility = VISIBLE
            item_no_data.visibility = GONE
            hideDataIsLoading()

        }
    }

    override fun displayMoreArticles(articles: ArrayList<Article>) {
        if (articles.size < 1){
            adapter.showNoMoreData()
        } else {
            adapter.addItems(articles)
        }

    }

    override fun error(msg: String) {
        runOnUiThread {

            refresh_layout.isRefreshing = false
            ToastUtil.showShort(this@MyArticlesActivity, msg)
            item_no_data.visibility = VISIBLE
            recycle_articles.visibility = GONE
            hideDataIsLoading()
        }
    }

    private lateinit var adapter: ArticlesAdapter


    private lateinit var presenter: MyArticlePresenter


    private val recycleScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            //判断是否能向上滑动
            if (!recycle_articles.canScrollVertically(1) && adapter.canLoadMore()) {
                adapter.showIsLoading()
                presenter.loadMoreArticle()
            }
        }
    }

    private val swipeRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        presenter.refreshArticles()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_articles_act)
        setupToolBar(toolbar, "My Articles")
        adapter = ArticlesAdapter()
        recycle_articles.layoutManager = LinearLayoutManager(this)
        recycle_articles.adapter = adapter
        presenter = MyArticlePresenter(this)
        refresh_layout.setOnRefreshListener(swipeRefreshListener)
        recycle_articles.addOnScrollListener(recycleScrollListener)
        item_no_data.setOnClickListener {
            recycle_articles.visibility = VISIBLE
            item_no_data.visibility = GONE
            adapter.clearItems()
            presenter.refreshArticles() }

        LiveDataBus.get()
            .with("article_deleted", String::class.java)
            .observe(this, Observer<String> {
                presenter.refreshArticles()
            })

    }

    override fun onResume() {
        super.onResume()
        presenter.refreshArticles()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
