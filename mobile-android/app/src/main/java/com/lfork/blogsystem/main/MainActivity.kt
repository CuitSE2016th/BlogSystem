package com.lfork.blogsystem.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.lfork.blogsystem.BaseActivity
import com.lfork.blogsystem.R
import com.lfork.blogsystem.main.following.FollowingFragment
import com.lfork.blogsystem.main.home.HomeFragment
import com.lfork.blogsystem.main.my.MyFragment
import kotlinx.android.synthetic.main.main_act.*

class MainActivity : BaseActivity() {

    private var fragments= ArrayList<Fragment>()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment(fragments[FRAG_INDEX_INDEX])
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                replaceFragment(fragments[FRAG_FOLLOWING_INDEX])
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_my -> {
                replaceFragment(fragments[FRAG_MY_INDEX])
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_act)
        initFragments()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    companion object {
        private const val FRAG_INDEX_INDEX = 0
        private const val FRAG_FOLLOWING_INDEX = 1
        private const val FRAG_MY_INDEX = 2
    }


    private fun initFragments() {
        fragments.add(HomeFragment())
        fragments.add(FollowingFragment())
        fragments.add(MyFragment())
        replaceFragment(fragments[FRAG_INDEX_INDEX])
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.main_frag, fragment)
        transaction.commit()
    }
}
