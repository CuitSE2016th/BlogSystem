package com.lfork.blogsystem.notifications

import com.lfork.blogsystem.data.article.Article
import com.lfork.blogsystem.data.notification.Notification

/**
 *
 * Created by 98620 on 2018/12/15.
 */
interface NotificationContract {
    interface View{
        fun refresh(data:ArrayList<Notification>)

        fun displayMore(data:ArrayList<Notification>)

        fun error(msg:String)
    }

    interface Presenter{

        /**
         * get latest 10 articles
         */
        fun refresh(){

        }

        fun loadMore(pageNumber:Int){

        }

        fun destroy(){

        }
    }
}