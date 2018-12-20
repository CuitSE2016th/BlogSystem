package com.lfork.blogsystem.notifications

import com.lfork.blogsystem.common.mvp.BasePresenter
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.notification.Notification
import com.lfork.blogsystem.data.notification.NotificationDataRepository
import com.lfork.blogsystem.data.user.UserDataRepository

/**
 *
 * Created by 98620 on 2018/12/15.
 */
open class NotificationPresenter(private var view: NotificationContract.View?) : BasePresenter(),NotificationContract.Presenter {

    val refreshDataCallBack = object : DataCallback<List<Notification>> {
        override fun succeed(data: List<Notification>) {
            view?.refresh(data as ArrayList<Notification>)
        }

        override fun failed(code: Int, log: String) {
           view?.error(log)
        }

    }

    val loadMoreDataCallBack = object : DataCallback<List<Notification>> {
        override fun succeed(data: List<Notification>) {
            view?.displayMore(data as ArrayList<Notification>)
        }

        override fun failed(code: Int, log: String) {
            view?.error(log)
        }
    }


    override fun refresh() {
        val cacheUser = UserDataRepository.userCache
        NotificationDataRepository.getLatestNotifications(
            refreshDataCallBack
        )
    }

    override fun loadMore(pageNumber: Int) {

        val cacheUser = UserDataRepository.userCache
        NotificationDataRepository.getLatestNotifications(
            loadMoreDataCallBack
        )
    }

    override fun destroy() {
        super.destroy()
        view = null
    }


}