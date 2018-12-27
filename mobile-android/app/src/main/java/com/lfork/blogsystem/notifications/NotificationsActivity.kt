package com.lfork.blogsystem.notifications

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.lfork.blogsystem.R
import com.lfork.blogsystem.utils.setupToolBar
import kotlinx.android.synthetic.main.notifications_act.*

class NotificationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notifications_act)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.article_main_container, NotificationsFragment())
                .commitNow()

            setupToolBar(toolbar, "Notifications")
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
