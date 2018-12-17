package com.lfork.blogsystem.usermainpage

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lfork.blogsystem.R

class UserMainPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_main_page_act)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.article_main_container, UserMainPageFragment.newInstance())
                .commitNow()
        }
    }

    companion object {

        const val PARAM_ACCOUNT = "param_account"

        fun startActivity(context: Context, account: String) {
            val intent = Intent(context, UserMainPageActivity::class.java)
            intent.putExtra(PARAM_ACCOUNT, account)
            context.startActivity(intent)
        }
    }

}
