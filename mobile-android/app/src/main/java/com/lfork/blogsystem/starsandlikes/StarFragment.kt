package com.lfork.blogsystem.starsandlikes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.R
import com.lfork.blogsystem.common.fragment.ArticlesFragment
import com.lfork.blogsystem.common.mvp.ArticleContract
import com.lfork.blogsystem.common.mvp.ArticlePresenter
import com.lfork.blogsystem.data.article.ArticleDataRepository

class StarFragment : ArticlesFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (root == null) {
            root = inflater.inflate(R.layout.star_frag, container, false)
            presenter = StarPresenter(this)
            super.onCreateView(inflater, container, savedInstanceState)
        }
        return root
    }


    inner class StarPresenter(private var view: ArticleContract.View?) : ArticlePresenter(view) {
        override fun refreshArticles() {
            ArticleDataRepository.getStaredArticles(
                BlogApplication.token!!,
                1,
                10,
                refreshDataCallBack2
            )
        }

        override fun loadMoreArticle(loadMoreAction: (() -> Unit)?) {
            super.loadMoreArticle{ ArticleDataRepository.getStaredArticles(
                BlogApplication.token!!,
                nextPage,
                10,
                loadMoreDataCallBack2
            )}

        }

        override fun destroy() {
            super.destroy()
            view = null
        }
    }
}
