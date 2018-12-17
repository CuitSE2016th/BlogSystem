package com.lfork.blogsystem.data.article.remote

import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.Test
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.article.Article
import com.lfork.blogsystem.data.article.ArticleDataSource
import java.util.*

/**
 *
 * @author 98620
 * @date 2018/12/15
 */
class ArticleRemoteDataSource : ArticleDataSource {
    override fun loadMoreUsesArticles(
        pageNumber: Int,
        account: String,
        token: String,
        callback: DataCallback<List<Article>>
    ) {
        BlogApplication.doAsyncTask {
            Thread.sleep(1000)
            val items = ArrayList<Article>(0);
            for (i in 1..10) {
                val it = Article()
                it.id = i.toString()
                it.title = "Android Studio3.$pageNumber.$i  Released~"
                it.editTime = Date().toString()
                it.coverUrl = Test.getRandomImages()
                it.abstract =
                        "the ${i}th description ,length test.big Text test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test"
                items.add(it)
            }
            callback.succeed(items)
        }
    }

    override fun getUsesArticles(
        account: String,
        token: String,
        callback: DataCallback<List<Article>>
    ) {

        BlogApplication.doAsyncTask {
            Thread.sleep(1200)
            val items = ArrayList<Article>(0);
            for (i in 1..10) {
                val it = Article()
                it.id = i.toString()
                it.title = "Android Studio3.1.$i Released~"
                it.editTime = Date().toString()
                it.coverUrl = Test.getRandomImages()
                it.abstract =
                        "the ${i}th description ,length test.big Text test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test"
                items.add(it)
            }
            callback.succeed(items)
        }
    }

}
