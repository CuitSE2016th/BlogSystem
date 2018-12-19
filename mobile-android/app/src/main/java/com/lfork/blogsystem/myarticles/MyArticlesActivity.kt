package com.lfork.blogsystem.myarticles

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lfork.blogsystem.BlogApplication.Companion.context
import com.lfork.blogsystem.R
import com.lfork.blogsystem.Test
import com.lfork.blogsystem.articledetail.ArticleDetailActivity.Companion.openArticleDetail
import com.lfork.blogsystem.base.databinding.ImageBinding
import com.lfork.blogsystem.data.article.Article
import com.lfork.blogsystem.utils.ToastUtil
import com.lfork.blogsystem.utils.setupToolBar
import kotlinx.android.synthetic.main.item_article_widen_style.view.*
import kotlinx.android.synthetic.main.my_articles_act.*
import kotlin.collections.ArrayList

class MyArticlesActivity : AppCompatActivity(), MyArticleContract.View {
    override fun refreshArticles(articles: ArrayList<Article>) {
        runOnUiThread {
            adapter.refreshItems(articles)
            refresh_layout.isRefreshing = false
        }
    }

    override fun displayMoreArticles(articles: ArrayList<Article>) {
        runOnUiThread {
            pageNumber++
            adapter.addItems(articles)
            refresh_layout.visibility = VISIBLE
            item_no_data.visibility = GONE
        }

    }

    override fun error(msg: String) {
        runOnUiThread {
            ToastUtil.showShort(this@MyArticlesActivity, msg)
            item_no_data.visibility = VISIBLE
            refresh_layout.visibility = GONE
        }
    }

    private lateinit var adapter: ArticlesAdapter

    private var pageNumber = 0

    private lateinit var presenter: MyArticlePresenter


    private val recycleScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            //判断是否能向上滑动
            if (!recycle_articles.canScrollVertically(1)) {
                presenter.loadMoreArticle(pageNumber)
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


    inner class ArticlesAdapter :
        RecyclerView.Adapter<ArticlesAdapter.MyViewHolder>() {
        private val items = ArrayList<Article>(0);

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
            when (viewType) {
                TYPE_TAIL -> {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_loadmore, parent, false)
                    MyViewHolder(view, TYPE_TAIL)
                }

                //TYPE_NORMAL
                else -> {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_article_widen_style, parent, false)
                    MyViewHolder(view, TYPE_NORMAL)
                }
            }

        private val TYPE_NORMAL = 0
        private val TYPE_TAIL = 1
        override fun getItemViewType(position: Int): Int {
            return if (position < itemCount - 1) {
                TYPE_NORMAL
            } else {
                TYPE_TAIL
            }
        }


        override fun getItemCount(): Int {
            return items.size + 1
        }

        override fun onBindViewHolder(holder: MyViewHolder, p1: Int) {

            if (p1 != itemCount - 1) {
                val item = items[p1]
                holder.title?.text = item.title
                ImageBinding.setImage(
                    holder.cover,
                    item.coverUrl,
                    R.drawable.ic_person_black_24dp
                )
//            //暂时没有description
                holder.abstract?.text = item.abstract
                holder.editTime?.text = item.editTime
//
                holder.itemView.setOnClickListener {
                    if (context != null) {
                        ToastUtil.showShort(context, "跳转到文章详情，当前的文章id为${item.id}")
                        openArticleDetail(this@MyArticlesActivity)
                    }
                }
            }

        }

        fun refreshItems(items: ArrayList<Article>) {
            this.items.clear()
            this.items.addAll(items)
            notifyDataSetChanged()
        }

        fun addItems(data: ArrayList<Article>) {
            items.addAll(data)
            notifyDataSetChanged()
//            notifyItemRangeInserted(items.size - data.size, items.size)
        }

        inner class MyViewHolder(itemView: View, itemType: Int) :
            RecyclerView.ViewHolder(itemView) {

            var title: TextView? = null
            var editTime: TextView? = null
            var cover: ImageView? = null
            var abstract: TextView? = null

            init {
                if (itemType == TYPE_NORMAL) {
                    title = itemView.title
                    editTime = itemView.edit_time
                    cover = itemView.cover
                    abstract = itemView.article_abstract
                } else {
                    //do nothing
                }
            }

        }

    }
}
