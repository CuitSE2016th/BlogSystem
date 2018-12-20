package com.lfork.blogsystem.data.notification

import com.lfork.blogsystem.data.DataCallback

/**
 *
 * Created by 98620 on 2018/12/11.
 */
object NotificationDataRepository :NotificationDataSource{
    val remote = NotificationRemoteDataSource()
    override fun getLatestNotifications(refreshDataCallBack: DataCallback<List<Notification>>) {
        remote.getLatestNotifications(refreshDataCallBack)
    }
}