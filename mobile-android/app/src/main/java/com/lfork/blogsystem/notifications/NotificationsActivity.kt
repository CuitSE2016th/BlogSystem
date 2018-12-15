package com.lfork.blogsystem.notifications

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lfork.blogsystem.R

class NotificationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notifications_act)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.article_main_container, NotificationsFragment.newInstance())
                .commitNow()
        }
    }

}
