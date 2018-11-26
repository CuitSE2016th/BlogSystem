package com.lfork.blogsystem.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.lfork.blogsystem.R
import com.lfork.blogsystem.main.explore.ExploreFragment
import com.lfork.blogsystem.main.following.FollowingFragment
import com.lfork.blogsystem.main.home.HomeFragment
import com.lfork.blogsystem.main.my.MyFragment
import kotlinx.android.synthetic.main.main_act.*

class MainActivity : AppCompatActivity() {

    private var fragments = HashMap<Int, Fragment>()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment(fragments[FRAG_HOME]!!)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_explore -> {
                replaceFragment(fragments[FRAG_EXPLORE]!!)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_my -> {
                replaceFragment(fragments[FRAG_MY]!!)
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
        private const val FRAG_HOME = 0
        private const val FRAG_FOLLOWING = 1
        private const val FRAG_MY = 2
        private const val FRAG_EXPLORE = 3
    }


    private fun initFragments() {
        fragments[FRAG_HOME] = HomeFragment()
        fragments[FRAG_FOLLOWING] = FollowingFragment()
        fragments[FRAG_MY] = MyFragment()
        fragments[FRAG_EXPLORE] = ExploreFragment()
        replaceFragment(fragments[FRAG_HOME]!!)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.main_frag, fragment)
        transaction.commit()
    }
}
