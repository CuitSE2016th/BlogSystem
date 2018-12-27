package com.lfork.blogsystem.notifications

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfork.blogsystem.R
import com.lfork.blogsystem.data.notification.Notification
import com.lfork.blogsystem.utils.ToastUtil
import kotlinx.android.synthetic.main.notifications_frag.view.*

class NotificationsFragment : Fragment(), NotificationContract.View {

    override fun refresh(data: ArrayList<Notification>) {
        activity?.runOnUiThread {
            root?.refresh_layout?.isRefreshing = false
            root?.item_no_data?.visibility = View.GONE
            root!!.data_container.visibility = View.VISIBLE


            if (data.size < 1){
                adapter.showNoMoreData()
            } else {
                adapter.refreshItems(data)
            }
        }
    }

    override fun displayMore(data: ArrayList<Notification>) {
        activity?.runOnUiThread {
            isLoadMore = false
            if (data.size < 1){
                adapter.showNoMoreData()
            } else {
                pageNumber++
                adapter.addItems(data)
            }
        }
    }

    override fun error(msg: String) {
        activity?.runOnUiThread {
            ToastUtil.showShort(context, msg)
            root?.refresh_layout?.isRefreshing = false
            isLoadMore = false
            root?.item_no_data?.visibility = View.VISIBLE
            root!!.data_container.visibility = View.GONE
        }
    }

    lateinit var adapter: NotificationsAdapter

    private var pageNumber = 0

    lateinit var presenter: NotificationPresenter

    private val recycleScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            //判断是否能向上滑动
            if (!root!!.recycle_notifications.canScrollVertically(1)) {
                loadMoreNotifications()
            }
        }
    }

    private var isLoadMore = false
    fun loadMoreNotifications() {
        if (isLoadMore){
            return
        }
        isLoadMore = true
        adapter.showIsLoading()
        presenter.loadMore(pageNumber)
    }

    private val swipeRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        presenter.refresh()
    }


    var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (root == null) {
            root = inflater.inflate(R.layout.notifications_frag, container, false)
            adapter = NotificationsAdapter()
            root!!.recycle_notifications.layoutManager = LinearLayoutManager(context)
            root!!.recycle_notifications.adapter = adapter
            presenter = NotificationPresenter(this)
            root!!.refresh_layout.setOnRefreshListener(swipeRefreshListener)
            root!!.recycle_notifications.addOnScrollListener(recycleScrollListener)

            root!!.item_no_data.setOnClickListener {
                adapter.clearItems()
                root?.refresh_layout?.isRefreshing = false
                root!!.item_no_data.visibility = View.GONE
                root!!.data_container.visibility = View.VISIBLE
                presenter.refresh()
            }
            presenter.refresh()
        }
        return root
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}
