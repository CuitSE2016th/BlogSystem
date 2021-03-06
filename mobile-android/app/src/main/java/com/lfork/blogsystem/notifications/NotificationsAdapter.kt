package com.lfork.blogsystem.notifications

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.TimeUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lfork.blogsystem.R
import com.lfork.blogsystem.data.notification.Notification
import com.lfork.blogsystem.utils.TimeUtil
import kotlinx.android.synthetic.main.item_article_widen_style.view.*
import kotlinx.android.synthetic.main.item_loadmore.view.*
import kotlinx.android.synthetic.main.item_notification.view.*

class NotificationsAdapter :
    RecyclerView.Adapter<NotificationsAdapter.MyViewHolder>() {
    private val items = ArrayList<Notification>(0);

    var headerLayoutId: Int = 0

    var headerView: View? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        when (viewType) {
            TYPE_TAIL -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_loadmore, parent, false)
                MyViewHolder(view, TYPE_TAIL)

            }

            TYPE_HEADER -> {
                val headerView: View = when {
                    headerView != null -> this@NotificationsAdapter.headerView!!
                    headerLayoutId == 0 -> View(parent.context)
                    else -> LayoutInflater.from(parent.context)
                        .inflate(headerLayoutId, parent, false)
                }
                MyViewHolder(headerView, TYPE_HEADER)
            }

            //TYPE_NORMAL
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_notification, parent, false)
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
                holder.content?.text = item.content
//            //暂时没有description
                holder.time?.text = item.time
            }
        }
    }

    fun refreshItems(items: ArrayList<Notification>) {
        this.items.clear()
        val n1 = Notification()
        n1.content="欢迎使用博客系统，这里是通知频道"
        n1.time =TimeUtil.getStandardTime()

        val n2 = Notification()
        n2.content = "2018年已经过去了，祝您在2019年取得新的成绩"
        n2.time = TimeUtil.getStandardTime()


        this.items.add(n1)
        this.items.add(n2)
        notifyDataSetChanged()
        hideDataIsLoading()
    }

    fun addItems(data: ArrayList<Notification>) {
        items.addAll(data)
        notifyDataSetChanged()
//            notifyItemRangeInserted(items.size - data.size, items.size)
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    private var footerView: View? = null

    inner class MyViewHolder(itemView: View, itemType: Int) :
        RecyclerView.ViewHolder(itemView) {

        var content: TextView? = null
        var time: TextView? = null
        var cover: ImageView? = null
        var abstract: TextView? = null

        init {
            when (itemType) {
                TYPE_NORMAL -> {
                    content = itemView.content
                    time = itemView.time
                    cover = itemView.cover
                    abstract = itemView.article_abstract
                }
                TYPE_HEADER -> {
//                    itemView.visibility = View.GONE
                }
                TYPE_TAIL ->{
                    footerView = itemView
                }
                else -> {
                    //do nothing
                }
            }
        }
    }

    fun showNoMoreData(){
        footerView!!.visibility = View.VISIBLE
        footerView!!.progressBar.visibility = View.GONE
        footerView!!.textView.text = footerView!!.context.getString(R.string.There_is_no_more_data)
    }

    fun hideDataIsLoading(){
        footerView!!.visibility = View.GONE
    }

    fun showIsLoading(){
        footerView!!.visibility = View.VISIBLE
        footerView!!.progressBar.visibility = View.VISIBLE
        footerView!!.textView.text = footerView!!.context.getString(R.string.data_is_loading)

    }
}