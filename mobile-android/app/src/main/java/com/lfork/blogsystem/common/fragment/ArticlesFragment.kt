package com.lfork.blogsystem.common.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfork.blogsystem.base.communication.LiveDataBus
import com.lfork.blogsystem.common.adapter.ArticlesAdapter
import com.lfork.blogsystem.common.mvp.ArticleContract
import com.lfork.blogsystem.common.mvp.ArticlePresenter
import com.lfork.blogsystem.data.article.Article
import com.lfork.blogsystem.utils.ToastUtil
import kotlinx.android.synthetic.main.main_home_latest_article_inner_frag.view.*

open class ArticlesFragment: Fragment(),ArticleContract.View {

    var isLoadingMore = false

    override fun refreshArticles(articles: ArrayList<Article>) {
        activity?.runOnUiThread {
            root?.refresh_layout?.isRefreshing = false
            root?.item_no_data?.visibility = View.GONE
            root!!.data_container.visibility = View.VISIBLE
            if (articles.size < 1){
                adapter.showNoMoreData()
            } else {
                adapter.refreshItems(articles)
            }
        }
    }

    override fun displayMoreArticles(articles: ArrayList<Article>) {
        activity?.runOnUiThread {
            isLoadingMore = false
            if (articles.size < 1){
                adapter.showNoMoreData()
            } else {
                adapter.addItems(articles)
            }

        }
    }

    override fun error(msg: String) {
        activity?.runOnUiThread {
            ToastUtil.showShort(context, msg)
            root?.refresh_layout?.isRefreshing = false
            isLoadingMore = false
            root?.item_no_data?.visibility = View.VISIBLE
            root!!.data_container.visibility = View.GONE
        }
    }
    lateinit var adapter: ArticlesAdapter

//    private var pageNumber = 0

    open var presenter: ArticlePresenter?=null

    private val recycleScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            //判断是否能向上滑动
            if (!root!!.recycle_articles.canScrollVertically(1) && adapter.canLoadMore()) {
                loadMoreArticles()
            }
        }
    }

    @Synchronized
    fun loadMoreArticles(){
        if(!isLoadingMore){
            isLoadingMore = true
            adapter.showIsLoading()
            presenter?.loadMoreArticle()
        }
    }

    private val swipeRefreshListener = SwipeRefreshLayout.OnRefreshListener {
            presenter?.refreshArticles()

    }


    var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (root == null) {
          throw Exception("必须先初始化root");
        }

        adapter = ArticlesAdapter()
        root!!.recycle_articles.layoutManager = LinearLayoutManager(context)
        root!!.recycle_articles.adapter = adapter
        if (presenter== null){
            presenter = ArticlePresenter(this)
        }

        root!!.refresh_layout.setOnRefreshListener(swipeRefreshListener)
        root!!.recycle_articles.addOnScrollListener(recycleScrollListener)

        root!!.item_no_data.setOnClickListener {
            adapter.clearItems()
            root?.refresh_layout?.isRefreshing = false
            root!!.item_no_data.visibility = View.GONE
            root!!.data_container.visibility = View.VISIBLE
            presenter?.refreshArticles()
        }

        presenter?.refreshArticles()


        LiveDataBus.get()
            .with("article_deleted", Int::class.java)
            .observe(this, Observer<Int> {
                adapter.notifyItemRemoved(it?:0)
            })
        return root

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

}