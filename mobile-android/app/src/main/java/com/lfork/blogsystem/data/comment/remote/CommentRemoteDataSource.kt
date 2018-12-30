package com.lfork.blogsystem.data.comment.remote

import com.lfork.blogsystem.RandomTest
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.comment.Comment
import com.lfork.blogsystem.data.comment.CommentDataSource
import com.lfork.blogsystem.data.comment.CommentListResponse
import kotlin.collections.ArrayList

/**
 *
 * @author 98620
 * @date 2018/12/15
 */
class CommentRemoteDataSource : CommentDataSource {
    override fun addComment(token: String, content: String, callback: DataCallback<Comment>) {
        val c = Comment(
            "11",
            "lfork${22 % 2}",
            RandomTest.getRandomImages(),
            null,
            null,
            "2018/12/17",
            "Tom",
            "100",
            "300",
            content
        )

        callback.succeed(c)
    }

    override fun deleteComment(token: String, cid: String, callback: DataCallback<String>) {
        callback.succeed("Succeed")
    }

    override fun addSubComment(
        token: String,
        pid: String,
        content: String,
        callback: DataCallback<Comment>
    ) {
        val c = Comment(
            "11",
            "lfork${22 % 2}",
            RandomTest.getRandomImages(),
            null,
            null,
            "2018/12/17",
            "Tom",
            "100",
            "300",
            content
        )

        callback.succeed(c)
    }

    override fun getComments(articleId: String, callback: DataCallback<CommentListResponse>) {
        val parentComment = ArrayList<Comment>()

        for (i in 0..9) {
            val c = Comment(
                "$i",
                "lfork${i % 2}",
                RandomTest.getRandomImages(),
                null,
                null,
                "2018/12/17",
                "Tom",
                "100",
                "300",
                RandomTest.getRandomText()
            )

            parentComment.add(c)
        }

        /**
         * children for parentComment[1]
         */
        parentComment[1].children = ArrayList()
        for (i in 10..14) {
            val c = Comment(
                "$i",
                "lfork${i % 2}",
                "www.lfork/blog/images/1.png",
                parentComment[1].commentId,
                null,
                "2018/12/17",
                "Tom",
                "100",
                "300",
                RandomTest.getRandomText()
            )
            parentComment[1].children?.add(c)
        }


        /**
         * children for parentComment[5]
         */
        parentComment[5].children = ArrayList()
        for (i in 15..20) {
            val c = Comment(
                "$i",
                "lfork${i % 2}",
                "www.lfork/blog/images/1.png",
                parentComment[5].commentId,
                null,
                "2018/12/17",
                "Tom",
                "100",
                "300",
                RandomTest.getRandomText()
            )
            parentComment[5].children?.add(c)
        }

        val response=CommentListResponse()
        response.result = parentComment
        callback.succeed(response)
    }

//    override fun getArticle(articleId: String, callback: DataCallback<ArticleResponse>) {
//        api.getArticle(BlogApplication.token!!,articleId).enqueue(MyRetrofitCallBack(callback))
//    }
//
//    override fun getMyArticles(
//        token: String,
//        pageNumber: Int,
//        pageSize: Int,
//        callback: DataCallback<ArticleListResponse>
//    ) {
//        api.getMyArticles(token,pageNumber, pageSize).enqueue(MyRetrofitCallBack(callback))
//    }
//
//    override fun getLatestArticles(
//        pageNumber: Int,
//        pageSize: Int,
//        callback: DataCallback<ArticleListResponse>
//    ) {
//        api.getLatestArticles(pageNumber, pageSize).enqueue(MyRetrofitCallBack(callback))
//    }
//
//    private val api= CommetApi.create()
//    override fun publishOrEditArticle(
//        token: String,
//        title: String,
//        content: String,
//        callback: DataCallback<String>
//    ) {
//        api.publishArticle(token, title, content)
//            .enqueue(MyRetrofitCallBack(callback))
//    }
//
//    override fun uploadArticleImages(token: String, image: File, callback: DataCallback<ArrayList<String>>) {
//        val fileBody = RequestBody.create(MediaType.parse("image/*"),image)
//        val photo = MultipartBody.Part.createFormData("image", image.name, fileBody)
//        api.uploadArticleImage(token, photo)
//            .enqueue(MyRetrofitCallBack(callback))
//    }
//
//    override fun uploadArticleImages(token: String, image: File): Result<ArrayList<String>>? {
//        val fileBody = RequestBody.create(MediaType.parse("image/*"),image)
//        val photo = MultipartBody.Part.createFormData("image", image.name, fileBody)
//        return api.uploadArticleImage(token, photo).execute().body()
//
//    }
//
//    override fun loadMoreUsesArticles(
//        pageNumber: Int,
//        account: String,
//        token: String,
//        callback: DataCallback<List<Article>>
//    ) {
//        BlogApplication.doAsyncTask {
//            Thread.sleep(1000)
//            callback.succeed(getRandomArticles())
//        }
//    }
//
//
//
//    override fun getUsesArticles(
//        account: String,
//        token: String,
//        callback: DataCallback<List<Article>>
//    ) {
//
//        BlogApplication.doAsyncTask {
//            Thread.sleep(1200)
//            if (testFlag % 2 == 1){
//                callback.failed(0,"error")
//            } else {
//                callback.succeed(getRandomArticles())
//            }
//            testFlag++
//
//        }
//    }
//
//    override fun getLatestArticles(
//        callback: DataCallback<List<Article>>
//    ) {
//
//        BlogApplication.doAsyncTask {
//            Thread.sleep(1200)
//
//            val random = testFlag % 6
//            if (random == 4){
//                callback.failed(0,"error")
//            }  else if (random == 2 || random == 3) {
//                callback.succeed(ArrayList())
//            } else {
//                callback.succeed(getRandomArticles())
//            }
//            testFlag++
//
//        }
//    }
//
//    fun getRandomArticles():ArrayList<Article>{
//        val items = ArrayList<Article>(0);
//        for (i in 1..10) {
//            val it = Article()
//            it.id = i.toString()
//            it.title = RandomTest.getRandomTitles()
//            it.createTime = Date().toString()
//            it.coverUrl = RandomTest.getRandomImages()
//            it.abstract =
//                    "the ${i}th description ,length test.big Text test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test.length test"
//            items.add(it)
//        }
//
//        return items
//    }
//
//    companion object {
//        var testFlag = 0
//    }

}
