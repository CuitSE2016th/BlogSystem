package com.lfork.blogsystem.myarticles

import com.lfork.blogsystem.data.article.Article

/**
 *
 * Created by 98620 on 2018/12/15.
 */
interface MyArticleContract {
    interface View{
        fun refreshArticles(articles:ArrayList<Article>)

        fun displayMoreArticles(articles:ArrayList<Article>)

        fun error(msg:String)
    }

    interface Presenter{

        /**
         * get latest 10 articles
         */
        fun refreshArticles(){

        }

        fun loadMoreArticle(pageNumber:Int){

        }

        fun destroy(){

        }
    }
}