package com.lfork.blogsystem.main.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class HomeFragmentStatePagerAdapter(
    val items: ArrayList<Fragment>,
    fragmentManager: FragmentManager
) :
    FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(p0: Int) = items[p0]

    override fun getCount() = items.size

}