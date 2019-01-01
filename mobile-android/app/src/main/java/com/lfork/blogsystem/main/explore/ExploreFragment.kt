package com.lfork.blogsystem.main.explore

import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lfork.blogsystem.R
import com.lfork.blogsystem.RandomTest
import com.lfork.blogsystem.base.databinding.ImageBinding
import com.lfork.blogsystem.common.fragment.ArticlesFragment
import com.lfork.blogsystem.data.article.Theme
import kotlinx.android.synthetic.main.item_theme.view.*
import kotlinx.android.synthetic.main.main_explore_frag.view.*

class ExploreFragment : ArticlesFragment() {

    companion object {
        fun newInstance() = ExploreFragment()
    }


    private lateinit var themeAdapter: ThemesAdapter


    private val scrollListener = object : NestedScrollView.OnScrollChangeListener {
        override fun onScrollChange(p0: NestedScrollView?, p1: Int, p2: Int, p3: Int, p4: Int) {
            if (!root!!.scroll_view.canScrollVertically(1) ) {
                loadMoreArticles()
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (root == null) {
            root = inflater.inflate(R.layout.main_explore_frag, container, false)
            super.onCreateView(inflater, container, savedInstanceState)
            setupRecycleThemes()
            root!!.recycle_articles.isNestedScrollingEnabled = false
            root!!.scroll_view.setOnScrollChangeListener(scrollListener)
        }
        return root
    }

    private fun setupRecycleThemes() {
        root!!.recycle_themes.layoutManager = GridLayoutManager(context, 4)
        themeAdapter = ThemesAdapter()
        root!!.recycle_themes.adapter = themeAdapter

    }

    inner class ThemesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        var items = ArrayList<Theme>()

        init {
            for (i in 1..16) {
                items.add(Theme(RandomTest.getRandomNames(), RandomTest.getRandomImages()))
            }
        }


        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_theme, parent, false)
            return ThemesViewHolder(view)
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, p1: Int) {
            holder.itemView.name_theme.text = items[p1].name
            ImageBinding.setImage(holder.itemView.icon_theme, items[p1].imageUrl)
        }

        inner class ThemesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        }
    }
}
