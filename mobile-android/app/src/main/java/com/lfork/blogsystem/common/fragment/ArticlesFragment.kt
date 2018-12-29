package com.lfork.blogsystem.common.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfork.blogsystem.common.adapter.ArticlesAdapter
import com.lfork.blogsystem.common.mvp.ArticleContract
import com.lfork.blogsystem.common.mvp.ArticlePresenter
import com.lfork.blogsystem.data.article.Article
import com.lfork.blogsystem.utils.ToastUtil
import kotlinx.android.synthetic.main.main_home_latest_article_inner_frag.view.*
import java.lang.Exception

open class ArticlesFragment: Fragment(),ArticleContract.View {

    @Volatile
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
                pageNumber++
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

    private var pageNumber = 0

    open var presenter: ArticlePresenter?=null

    private val recycleScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            //判断是否能向上滑动
            if (!root!!.recycle_articles.canScrollVertically(1)) {
                loadMoreArticles()
            }
        }
    }

    @Synchronized
    fun loadMoreArticles(){
        Log.d("加载两次?", "enen?2$isLoadingMore $this")
        if(!isLoadingMore){
            isLoadingMore = true
            Log.d("加载两次?", "enen?1$isLoadingMore $this")
            adapter.showIsLoading()
            presenter?.loadMoreArticle(pageNumber)
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
        return root

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

}