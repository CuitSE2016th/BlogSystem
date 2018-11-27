package com.lfork.blogsystem.userinfo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lfork.blogsystem.R

class UserInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_info_act)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, UserInfoFragment.newInstance())
                    .commitNow()
        }
    }

}
