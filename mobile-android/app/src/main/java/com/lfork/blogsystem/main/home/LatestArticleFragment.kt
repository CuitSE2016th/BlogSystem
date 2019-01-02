package com.lfork.blogsystem.main.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import android.widget.LinearLayout
import com.lfork.blogsystem.R
import com.lfork.blogsystem.RandomTest
import com.lfork.blogsystem.base.databinding.ImageBinding
import com.lfork.blogsystem.common.fragment.ArticlesFragment
import com.lfork.blogsystem.common.mvp.ArticlePresenter
import com.lfork.blogsystem.data.article.Article
import com.ms.banner.Banner
import com.ms.banner.holder.BannerViewHolder
import dip2px

class LatestArticleFragment : ArticlesFragment() {


    override fun refreshArticles(articles: ArrayList<Article>) {
        super.refreshArticles(articles)
        activity?.runOnUiThread {
            val images = ArrayList<String>()
            images.add(RandomTest.getRandomImages())
            images.add(RandomTest.getRandomImages())
            images.add(RandomTest.getRandomImages())

            (adapter.headerView as Banner).setAutoPlay(true)
                .setPages(
                    images
                ) { CustomViewHolder() }
                .start()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**
         * 必须先初始化Root
         */
        if (root == null) {
            root = inflater.inflate(R.layout.main_home_latest_article_inner_frag, container, false)
            super.onCreateView(inflater, container, savedInstanceState)
            setupBanner()
        }
        return root
    }


    private fun setupBanner() {
        val banner = Banner(context)
        banner.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, dip2px(200f))
        adapter.headerView = banner
    }

    inner class CustomViewHolder : BannerViewHolder<String> {

        private var mCardView: CardView? = null
        private var mImageView: ImageView? = null

        @SuppressLint("InflateParams")
        override fun createView(context: Context): View {
            val view = LayoutInflater.from(context).inflate(R.layout.item_radius_banner, null)
            mCardView = view.findViewById(R.id.group)
            mImageView = view.findViewById(R.id.image)
            return view
        }

        override fun onBind(context: Context, position: Int, data: String) {
            // 数据绑定
            ImageBinding.setImage(mImageView, data)
        }
    }

}
