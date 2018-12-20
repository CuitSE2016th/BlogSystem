package com.lfork.blogsystem.data.notification

import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.data.DataCallback
import java.util.*

class NotificationRemoteDataSource : NotificationDataSource {
    override fun getLatestNotifications(refreshDataCallBack: DataCallback<List<Notification>>) {
        BlogApplication.doAsyncTask {
            Thread.sleep(1200)
            val items = ArrayList<Notification>(0);
            for (i in 1..10) {
                val it = Notification()
                it.content = "Android Studio3.1.$i Released~"
                it.time = Date().toString()
                items.add(it)
            }

            val random = testFlag % 6
            if (random == 4){
                refreshDataCallBack.failed(0,"error")
            }  else if (random == 2 || random == 3) {
                refreshDataCallBack.succeed(ArrayList())
            } else {
                refreshDataCallBack.succeed(items)
            }
            testFlag++
        }



    }

    companion object {
        var testFlag = 0
    }

}