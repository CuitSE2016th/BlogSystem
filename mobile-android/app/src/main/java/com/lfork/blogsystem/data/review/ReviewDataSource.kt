package com.lfork.blogsystem.data.review

/**
 *
 * Created by 98620 on 2018/12/11.
 */
interface ReviewDataSource {
    fun addReview()

    fun deleteReview()

    fun getReviews()
}