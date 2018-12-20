package com.lfork.blogsystem.starsandlikes

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment

import com.lfork.blogsystem.R
import com.lfork.blogsystem.main.home.HomeFragmentStatePagerAdapter
import kotlinx.android.synthetic.main.star_like_act.*

import net.lucode.hackware.magicindicator.FragmentContainerHelper
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView


class StarLikeActivity : AppCompatActivity() {



    private lateinit var viewModel: StarViewModel


    @SuppressLint("UseSparseArrays")
    private var fragments = HashMap<Int, Fragment>()

    private var mFragmentContainerHelper: FragmentContainerHelper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.star_like_act)
        if (savedInstanceState == null) {
            viewModel = ViewModelProviders.of(this).get(StarViewModel::class.java)
            setupSubFragIndicator()
            setupViewPager()
            ViewPagerHelper.bind(magicIndicator, container);
        }
    }


    private fun setupSubFragIndicator() {
        val magicIndicator = magicIndicator as MagicIndicator
        val commonNavigator = CommonNavigator(this)

        commonNavigator.adapter = object : CommonNavigatorAdapter() {

            override fun getCount(): Int {
                return viewModel.mTitleDataList.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val colorTransitionPagerTitleView = ColorTransitionPagerTitleView(context)
                colorTransitionPagerTitleView.normalColor = Color.GRAY
                colorTransitionPagerTitleView.selectedColor = Color.BLACK
                colorTransitionPagerTitleView.text = viewModel.mTitleDataList[index]
                colorTransitionPagerTitleView.setOnClickListener {
                    container.currentItem = index;
                }
                return colorTransitionPagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                indicator.mode = LinePagerIndicator.MODE_WRAP_CONTENT
                return indicator
            }
        }
        magicIndicator.navigator = commonNavigator

        mFragmentContainerHelper = FragmentContainerHelper(magicIndicator);

    }

    private fun setupViewPager() {
        val viewPager = container
        val innerFragments = ArrayList<Fragment>()
        innerFragments.add(LikeFragment())
        innerFragments.add( StarFragment())
        viewPager.adapter = HomeFragmentStatePagerAdapter(innerFragments,supportFragmentManager)
    }


}
