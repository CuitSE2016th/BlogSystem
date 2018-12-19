package com.lfork.blogsystem.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.lfork.blogsystem.R
import com.lfork.blogsystem.main.explore.ExploreFragment
import com.lfork.blogsystem.main.home.HomeFragment
import com.lfork.blogsystem.main.my.MyFragment
import kotlinx.android.synthetic.main.main_act.*

class MainActivity : AppCompatActivity() {

    private var fragments = HashMap<Int, Fragment>()

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
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
        private const val FRAG_MY = 1
        private const val FRAG_EXPLORE = 2
    }

    private fun initFragments() {
        if(fragments.size < 1){
            fragments[FRAG_HOME] = HomeFragment()
            fragments[FRAG_MY] = MyFragment()
            fragments[FRAG_EXPLORE] = ExploreFragment()
            replaceFragment(fragments[FRAG_HOME]!!)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        if (!fragment.isAdded) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_frag, fragment, fragment.tag)
                .commit()
        }

        for (i in fragments) {
            supportFragmentManager.beginTransaction()
                .hide(i.value)
                .commit()
        }
        supportFragmentManager.beginTransaction()
            .show(fragment)
            .commit()
    }

    /**
     * 解决Fragment重影
     */
    override fun onAttachFragment(fragment: Fragment?) {
        super.onAttachFragment(fragment)
        //当前的界面的保存状态，只是重新让新的Fragment指向了原本未被销毁的fragment，它就是onAttach方法对应的Fragment对象
        if (fragments[FRAG_EXPLORE] == null && fragment is ExploreFragment) {
            fragments[FRAG_EXPLORE] = fragment

        } else if (fragments[FRAG_HOME] == null && fragment is HomeFragment) {

            fragments[FRAG_HOME] = fragment

        } else if (fragments[FRAG_MY] == null && fragment is MyFragment) {

            fragments[FRAG_MY] = fragment
        }

    }
}
