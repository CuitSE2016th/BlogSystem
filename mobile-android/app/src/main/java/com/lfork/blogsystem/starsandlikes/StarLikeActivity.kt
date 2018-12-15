package com.lfork.blogsystem.starsandlikes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lfork.blogsystem.R


class StarLikeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.star_like_act)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.article_main_container, StarFragment.newInstance())
                .commitNow()
        }
    }

}
