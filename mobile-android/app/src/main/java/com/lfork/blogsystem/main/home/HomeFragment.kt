package com.lfork.blogsystem.main.home

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.lfork.blogsystem.R
import com.lfork.blogsystem.articleedit.ArticleEditorActivity
import com.lfork.blogsystem.base.databinding.ImageBinding
import com.lfork.blogsystem.utils.startActivity
import com.ms.banner.holder.BannerViewHolder
import kotlinx.android.synthetic.main.main_home_frag.view.*
import java.util.*
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import android.graphics.Color
import com.lfork.blogsystem.BlogApplication.Companion.isSignIn
import com.lfork.blogsystem.login.LoginActivity
import com.lfork.blogsystem.utils.ToastUtil
import net.lucode.hackware.magicindicator.FragmentContainerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {


    private lateinit var viewModel: HomeViewModel


    @SuppressLint("UseSparseArrays")
    private var fragments = HashMap<Int, Fragment>()

    private var mFragmentContainerHelper: FragmentContainerHelper? = null

    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (root == null) {
            viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
            root = inflater.inflate(R.layout.main_home_frag, container, false)
            root!!.btn_edit.setOnClickListener {
                if (isSignIn) {
                    context?.startActivity<ArticleEditorActivity>()
                } else {
                    context?.startActivity<LoginActivity>()
                }
            }
            setupSubFragIndicator()
            setupViewPager()
            ViewPagerHelper.bind(root!!.magicIndicator, root!!.container);
        }
        return root

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }




    private fun setupSubFragIndicator() {
        val magicIndicator = root!!.magicIndicator as MagicIndicator
        val commonNavigator = CommonNavigator(context)

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
                    if (isSignIn || index == 0){
                        root!!.container.currentItem = index;
                    } else{
                        ToastUtil.showLong(context, "Please sign in.")
                        activity?.startActivity<LoginActivity>()
                    }
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

        // invoke wh
    }

    private fun setupViewPager() {
        val viewPager = root!!.container
        val innerFragments = ArrayList<Fragment>()
        innerFragments.add(LatestArticleFragment())
        innerFragments.add( FocusArticleFragment())
        viewPager.adapter = HomeFragmentStatePagerAdapter(innerFragments,fragmentManager!!)
    }

}
