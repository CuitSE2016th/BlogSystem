package com.lfork.blogsystem.userinfo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.lfork.blogsystem.R
import com.lfork.blogsystem.utils.setupToolBar
import kotlinx.android.synthetic.main.register_act.*

class UserInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_info_act)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, UserInfoFragment.newInstance())
                    .commitNow()
        }

        setupToolBar(toolbar, resources.getString(R.string.title_activity_my_information))
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
