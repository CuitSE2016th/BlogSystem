package com.lfork.blogsystem.follow

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.lfork.blogsystem.R
import kotlinx.android.synthetic.main.follow_act.*

class FollowActivity : AppCompatActivity() {

    companion object {
        private const val TARGET_FRAG_PARAM = "target_frag"
        const val FOLLOWER_FRAG = 1
        const val FOLLOWING_FRAG = 2

        @JvmStatic
        fun startFollowActivity(context: Context, targetFrag: Int) {
            val intent = Intent(context, FollowActivity::class.java)
            intent.putExtra(TARGET_FRAG_PARAM, targetFrag)
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.follow_act)

        if (savedInstanceState == null) {
            replaceFragment(intent.getIntExtra("target_frag", 0))
        }
    }

    private fun setupToolBar(title:String) {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)  //设置返回按钮，需要在监听里面实现返回功能   onOptionsItemSelected(MenuItem item)
        actionBar.title = title
    }

    private fun replaceFragment(fragId: Int) {
        when (fragId) {
            FOLLOWING_FRAG ->{
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, FollowingFragment.newInstance())
                        .commitNow()
                setupToolBar(resources.getString(R.string.title_following));
            }

            FOLLOWER_FRAG -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, FollowerFragment.newInstance())
                        .commitNow()
                setupToolBar(resources.getString(R.string.title_follower));
            }

            else -> finish()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }




}
