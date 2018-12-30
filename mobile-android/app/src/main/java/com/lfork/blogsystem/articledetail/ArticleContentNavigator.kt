package com.lfork.blogsystem.articledetail

import com.lfork.blogsystem.base.viewmodel.Navigator
import com.lfork.blogsystem.data.article.ArticleResponse

interface ArticleContentNavigator: Navigator {
    fun showContent(htmlContent:ArticleResponse)




}