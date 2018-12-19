//package com.lfork.blogsystem.base.adapter
//
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import com.lfork.blogsystem.BlogApplication
//import com.lfork.blogsystem.R
//import com.lfork.blogsystem.articledetail.ArticleDetailActivity
//import com.lfork.blogsystem.base.databinding.ImageBinding
//import com.lfork.blogsystem.data.article.Article
//import com.lfork.blogsystem.utils.ToastUtil
//import kotlinx.android.synthetic.main.item_article_widen_style.view.*
//
//class ArticlesAdapter :
//    RecyclerView.Adapter<ArticlesAdapter.MyViewHolder>() {
//    private val items = ArrayList<Article>(0);
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
//        when (viewType) {
//            TYPE_TAIL -> {
//                val view = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.item_loadmore, parent, false)
//                MyViewHolder(view, TYPE_TAIL)
//            }
//
//            //TYPE_NORMAL
//            else -> {
//                val view = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.item_article_widen_style, parent, false)
//                MyViewHolder(view, TYPE_NORMAL)
//            }
//        }
//
//    private val TYPE_NORMAL = 0
//    private val TYPE_TAIL = 1
//    override fun getItemViewType(position: Int): Int {
//        return if (position < itemCount - 1) {
//            TYPE_NORMAL
//        } else {
//            TYPE_TAIL
//        }
//    }
//
//
//    override fun getItemCount(): Int {
//        return items.size + 1
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, p1: Int) {
//
//        if (p1 != itemCount - 1) {
//            val item = items[p1]
//            holder.title?.text = item.title
//            ImageBinding.setImage(
//                holder.cover,
//                item.coverUrl,
//                R.drawable.ic_person_black_24dp
//            )
////            //暂时没有description
//            holder.abstract?.text = item.abstract
//            holder.editTime?.text = item.editTime
////
//            holder.itemView.setOnClickListener {
//                if (BlogApplication.context != null) {
//                    ToastUtil.showShort(BlogApplication.context, "跳转到文章详情，当前的文章id为${item.id}")
//                    ArticleDetailActivity.openArticleDetail(this@MyArticlesActivity)
//                }
//            }
//        }
//
//    }
//
//    fun refreshItems(items: ArrayList<Article>) {
//        this.items.clear()
//        this.items.addAll(items)
//        notifyDataSetChanged()
//    }
//
//    fun addItems(data: ArrayList<Article>) {
//        items.addAll(data)
//        notifyDataSetChanged()
////            notifyItemRangeInserted(items.size - data.size, items.size)
//    }
//
//    inner class MyViewHolder(itemView: View, itemType: Int) :
//        RecyclerView.ViewHolder(itemView) {
//
//        var title: TextView? = null
//        var editTime: TextView? = null
//        var cover: ImageView? = null
//        var abstract: TextView? = null
//
//        init {
//            if (itemType == TYPE_NORMAL) {
//                title = itemView.title
//                editTime = itemView.edit_time
//                cover = itemView.cover
//                abstract = itemView.article_abstract
//            } else {
//                //do nothing
//            }
//        }
//
//    }
//
//}