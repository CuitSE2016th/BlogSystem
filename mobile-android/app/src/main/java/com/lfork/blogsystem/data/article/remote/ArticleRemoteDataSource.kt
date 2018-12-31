package com.lfork.blogsystem.data.article.remote

import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.RandomTest
import com.lfork.blogsystem.base.network.HTTPCallBack
import com.lfork.blogsystem.base.network.Result
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.article.Article
import com.lfork.blogsystem.data.article.ArticleDataSource
import com.lfork.blogsystem.data.article.ArticleListResponse
import com.lfork.blogsystem.data.article.ArticleDetailResponse
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

/**
 *
 * @author 98620
 * @. 2018/12/15
 */
class ArticleRemoteDataSource : ArticleDataSource {
    override fun starArticle(token: String, articleId: String, callback: DataCallback<String>) {
        api.starArticle(token, articleId).enqueue(HTTPCallBack(callback))
    }

    override fun likeArticle(token: String, articleId: String, callback: DataCallback<String>) {
        api.likeArticle(token, articleId).enqueue(HTTPCallBack(callback))
    }

    override fun deleteArticle(token: String, articleId: String, callback: DataCallback<String>) {
        api.deleteArticle(token, articleId).enqueue(HTTPCallBack(callback))
    }

    override fun getArticle(articleId: String, callback: DataCallback<ArticleDetailResponse>) {
        api.getArticle(BlogApplication.token!!, articleId).enqueue(HTTPCallBack(callback))
    }

    override fun getMyArticles(
        token: String,
        pageNumber: Int,
        pageSize: Int,
        callback: DataCallback<ArticleListResponse>
    ) {
        api.getMyArticles(token, pageNumber, pageSize).enqueue(HTTPCallBack(callback))
    }

    override fun getLatestArticles(
        pageNumber: Int,
        pageSize: Int,
        callback: DataCallback<ArticleListResponse>
    ) {
        api.getLatestArticles(pageNumber, pageSize).enqueue(HTTPCallBack(callback))
    }


    override fun publishOrEditArticle(
        token: String,
        title: String,
        content: String,
        callback: DataCallback<String>
    ) {
        api.publishArticle(token, title, content)
            .enqueue(HTTPCallBack(callback))
    }

    override fun uploadArticleImages(
        token: String,
        image: File,
        callback: DataCallback<ArrayList<String>>
    ) {
        val fileBody = RequestBody.create(MediaType.parse("image/*"), image)
        val photo = MultipartBody.Part.createFormData("image", image.name, fileBody)
        api.uploadArticleImage(token, photo)
            .enqueue(HTTPCallBack(callback))
    }

    override fun uploadArticleImages(token: String, image: File): Result<ArrayList<String>>? {
        val fileBody = RequestBody.create(MediaType.parse("image/*"), image)
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
            if (testFlag % 2 == 1) {
                callback.failed(0, "error")
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
            if (random == 4) {
                callback.failed(0, "error")
            } else if (random == 2 || random == 3) {
                callback.succeed(ArrayList())
            } else {
                callback.succeed(getRandomArticles())
            }
            testFlag++

        }
    }

    fun getRandomArticles(): ArrayList<Article> {
        val items = ArrayList<Article>(0);
        for (i in 1..10) {
            val it = Article()
            it.id = i.toString()
            it.title = RandomTest.getRandomTitles()
            it.createTime = System.currentTimeMillis().toString()
            it.imageUrl = RandomTest.getRandomImages()
            it.abstract =
                    "the ${i}th description ,length test.big Text test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test"
            items.add(it)
        }

        return items
    }

    private val api: ArticleApi = ArticleApi.create()

    companion object {
        var testFlag = 0
    }

}
