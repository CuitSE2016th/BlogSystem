package com.lfork.blogsystem.common.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.R
import com.lfork.blogsystem.articledetail.ArticleDetailActivity
import com.lfork.blogsystem.base.databinding.ImageBinding
import com.lfork.blogsystem.data.article.Article
import com.lfork.blogsystem.utils.ToastUtil
import kotlinx.android.synthetic.main.item_article_widen_style.view.*
import kotlinx.android.synthetic.main.item_loadmore.view.*

class ArticlesAdapter :
    RecyclerView.Adapter<ArticlesAdapter.MyViewHolder>() {
    private val items = ArrayList<Article>(0);

    var headerLayoutId: Int = 0

    var headerView: View? = null

    var footerView: View? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        when (viewType) {
            TYPE_TAIL -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_loadmore, parent, false)
                MyViewHolder(view, TYPE_TAIL)
            }

            TYPE_HEADER -> {
                val headerView: View = when {
                    headerView != null -> this@ArticlesAdapter.headerView!!
                    headerLayoutId == 0 -> View(parent.context)
                    else -> LayoutInflater.from(parent.context)
                        .inflate(headerLayoutId, parent, false)
                }
                MyViewHolder(headerView, TYPE_HEADER)
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
    private val TYPE_HEADER = 2
    override fun getItemViewType(position: Int) = when (position) {
        //头部
        0 -> {
            TYPE_HEADER
        }
        //尾部
        itemCount - 1 -> {
            TYPE_TAIL
        }
        else -> {
            TYPE_NORMAL
        }
    }


    override fun getItemCount(): Int {
        return items.size + 2
    }

    override fun onBindViewHolder(holder: MyViewHolder, p1: Int) {

        when (p1) {
            //头部
            0 -> {

            }

            //尾部
            itemCount - 1 -> {

            }
            else -> {
                //减一是因为有个Header View
                val item = items[p1 - 1]
                holder.title?.text = item.title
                ImageBinding.setImage(
                    holder.cover,
                    item.coverUrl,
                    R.drawable.ic_person_black_24dp
                )
//            //暂时没有description
                holder.abstract?.text = item.abstract
                holder.editTime?.text = item.createTime
//
                holder.itemView.setOnClickListener {
                    if (BlogApplication.context != null) {
                        ToastUtil.showShort(
                            BlogApplication.context,
                            "跳转到文章详情，当前的文章id为${item.id}"
                        )
                        ArticleDetailActivity.openArticleDetail(it.context, item.id!!)
                    }
                }
            }
        }
    }

    fun canLoadMore(): Boolean {
        return footerView?.visibility != View.VISIBLE
    }

    fun refreshItems(items: ArrayList<Article>) {
        this.items.clear()
        this.items.addAll(items)
        hideLoadMore()
        notifyDataSetChanged()
    }

    fun addItems(data: ArrayList<Article>) {
        items.addAll(data)
        hideLoadMore()
        notifyDataSetChanged()

//            notifyItemRangeInserted(items.size - data.size, items.size)
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View, itemType: Int) :
        RecyclerView.ViewHolder(itemView) {

        var title: TextView? = null
        var editTime: TextView? = null
        var cover: ImageView? = null
        var abstract: TextView? = null

        init {
            when (itemType) {
                TYPE_NORMAL -> {
                    title = itemView.title
                    editTime = itemView.edit_time
                    cover = itemView.cover
                    abstract = itemView.article_abstract
                }
                TYPE_HEADER -> {
                }
                else -> {
                    footerView = itemView
                    footerView?.visibility = View.INVISIBLE
                }
            }
        }
    }

    fun showNoMoreData() {
        footerView?.visibility = View.VISIBLE
        footerView?.textView?.text = footerView!!.context.getString(R.string.There_is_no_more_data)
        footerView?.progressBar?.visibility = View.INVISIBLE
    }

    fun showIsLoading() {
        footerView?.visibility = View.VISIBLE
        footerView?.textView?.text = footerView!!.context.getString(R.string.data_is_loading)
        footerView?.progressBar?.visibility = View.VISIBLE
    }

    fun hideLoadMore() {
        footerView?.visibility = View.INVISIBLE
    }
}