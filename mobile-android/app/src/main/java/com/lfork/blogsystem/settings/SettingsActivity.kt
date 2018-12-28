package com.lfork.blogsystem.settings

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.R
import com.lfork.blogsystem.base.communication.LiveDataBus
import com.lfork.blogsystem.login.LoginActivity
import com.lfork.blogsystem.utils.DataCleanManager
import com.lfork.blogsystem.utils.startActivity
import kotlinx.android.synthetic.main.settings_act.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_act)
        if (!BlogApplication.isSignIn){
            btn_sign_out.visibility = View.GONE
        }
        btn_sign_out.setOnClickListener {
            DataCleanManager.cleanSharedPreference(this)
            this@SettingsActivity.startActivity<LoginActivity>()
            LiveDataBus.get().with("sign_out_succeed").value = "退出登录";
            BlogApplication.clearUserInfo()
            finish()

        }


    }
}
