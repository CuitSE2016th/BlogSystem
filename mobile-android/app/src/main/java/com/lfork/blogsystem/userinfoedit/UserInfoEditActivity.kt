package com.lfork.blogsystem.userinfoedit

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.lfork.blogsystem.R
import com.lfork.blogsystem.utils.setupToolBar
import kotlinx.android.synthetic.main.register_act.*


class UserInfoEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_infoedit_act)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, UserInfoEditFragment.newInstance())
                .commitNow()
        }

        var isGranted = false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isGranted = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        }

        Log.d("Permission Test", "eeee $isGranted")

        setupToolBar(toolbar, resources.getString(R.string.title_activity_my_information))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //把回调传递给fragment
        supportFragmentManager.fragments[0].onActivityResult(requestCode, resultCode, data)
    }

}
