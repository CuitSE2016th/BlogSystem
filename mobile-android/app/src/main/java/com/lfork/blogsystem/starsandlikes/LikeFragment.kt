package com.lfork.blogsystem.starsandlikes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfork.blogsystem.BlogApplication.Companion.token
import com.lfork.blogsystem.R
import com.lfork.blogsystem.common.fragment.ArticlesFragment
import com.lfork.blogsystem.common.mvp.ArticleContract
import com.lfork.blogsystem.common.mvp.ArticlePresenter
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.article.ArticleDataRepository
import com.lfork.blogsystem.data.article.ArticleListResponse

class LikeFragment : ArticlesFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (root == null) {
            root = inflater.inflate(R.layout.like_frag, container, false)
            presenter = LikePresenter(this)
            super.onCreateView(inflater, container, savedInstanceState)
        }
        // Inflate the layout for this fragment
        return root
    }

    inner class LikePresenter(private var view: ArticleContract.View?) : ArticlePresenter(view) {
        override fun refreshArticles() {
            ArticleDataRepository.getFollowingArticles(token!!, 1, 10, refreshDataCallBack2)
        }

        override fun loadMoreArticle(loadMoreAction: (() -> Unit)?) {
            super.loadMoreArticle {
                ArticleDataRepository.getFollowingArticles(
                    token!!,
                    nextPage,
                    10,
                    loadMoreDataCallBack2
                )
            }

        }

        override fun destroy() {
            super.destroy()
            view = null
        }
    }

}
