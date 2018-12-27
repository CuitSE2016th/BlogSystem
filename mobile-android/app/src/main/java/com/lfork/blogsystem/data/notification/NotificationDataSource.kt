package com.lfork.blogsystem.data.notification

import com.lfork.blogsystem.data.DataCallback

/**
 *
 * Created by 98620 on 2018/12/11.
 */
interface NotificationDataSource {

//    fun getNotifications()
//
//    fun addNotification()

    fun getLatestNotifications(refreshDataCallBack: DataCallback<List<Notification>>)


}