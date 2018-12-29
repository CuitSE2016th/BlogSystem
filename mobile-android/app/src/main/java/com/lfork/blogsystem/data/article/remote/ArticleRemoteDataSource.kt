package com.lfork.blogsystem.data.article.remote

import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.RandomTest
import com.lfork.blogsystem.base.network.MyRetrofitCallBack
import com.lfork.blogsystem.base.network.Result
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.article.Article
import com.lfork.blogsystem.data.article.ArticleDataSource
import com.lfork.blogsystem.data.article.ArticleResponse
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

/**
 *
 * @author 98620
 * @date 2018/12/15
 */
class ArticleRemoteDataSource : ArticleDataSource {
    override fun getLatestArticles(
        pageNumber: Int,
        pageSize: Int,
        callback: DataCallback<ArticleResponse>
    ) {
        api.getLatestArticles(pageNumber, pageSize).enqueue(MyRetrofitCallBack(callback))
    }

    private val api: ArticleApi = ArticleApi.create()
    override fun publishOrEditArticle(
        token: String,
        title: String,
        content: String,
        callback: DataCallback<String>
    ) {
        api.publishArticle(token, title, content)
            .enqueue(MyRetrofitCallBack(callback))
    }

    override fun uploadArticleImages(token: String, image: File, callback: DataCallback<String>) {
        val fileBody = RequestBody.create(MediaType.parse("image/*"),image)
        val photo = MultipartBody.Part.createFormData("image", image.name, fileBody)
        api.uploadArticleImage(token, photo)
            .enqueue(MyRetrofitCallBack(callback))
    }

    override fun uploadArticleImages(token: String, image: File): Result<String>? {
        val fileBody = RequestBody.create(MediaType.parse("image/*"),image)
        val photo = MultipartBody.Part.createFormData("image", image.name, fileBody)
        return api.uploadArticleImage(token, photo).execute().body()

    }

    override fun loadMoreUsesArticles(
        pageNumber: Int,
        account: String,
        token: String,
        callback: DataCallback<List<Article>>
    ) {
        BlogApplication.doAsyncTask {
            Thread.sleep(1000)
            callback.succeed(getRandomArticles())
        }
    }



    override fun getUsesArticles(
        account: String,
        token: String,
        callback: DataCallback<List<Article>>
    ) {

        BlogApplication.doAsyncTask {
            Thread.sleep(1200)
            if (testFlag % 2 == 1){
                callback.failed(0,"error")
            } else {
                callback.succeed(getRandomArticles())
            }
            testFlag++

        }
    }

    override fun getLatestArticles(
        callback: DataCallback<List<Article>>
    ) {

        BlogApplication.doAsyncTask {
            Thread.sleep(1200)

            val random = testFlag % 6
            if (random == 4){
                callback.failed(0,"error")
            }  else if (random == 2 || random == 3) {
                callback.succeed(ArrayList())
            } else {
                callback.succeed(getRandomArticles())
            }
            testFlag++

        }
    }

    fun getRandomArticles():ArrayList<Article>{
        val items = ArrayList<Article>(0);
        for (i in 1..10) {
            val it = Article()
            it.id = i.toString()
            it.title = RandomTest.getRandomTitles()
            it.createTime = Date().toString()
            it.coverUrl = RandomTest.getRandomImages()
            it.abstract =
                    "the ${i}th description ,length test.big Text test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test"
            items.add(it)
        }

        return items
    }

    companion object {
        var testFlag = 0
    }

}
