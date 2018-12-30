package com.lfork.blogsystem.data.comment

/**
 *
 * Created by 98620 on 2018/12/11.
 */
data class Comment(

    var commentId: String? = null,

    var userName: String? = null,

    var userPortrait: String? = null,

    /**
     * 评论依赖所依赖的对象：文章、父评论
     */
    var parentId: String? = null,

    /**
     * 如果评论本身就是child，那么它就不会再有child了。只有两层评论
     */
    var children: ArrayList<Comment>? = null,


    var time: String? = null,

    /**
     * 回复对象的信息。 ui展示: userName 回复了 replyTo
     */
    var replyTo: String? = null,

    /**
     * 点赞数
     */
    var likeCount: String? = null,

    /**
     * 收藏数
     */
    var startNumber: String? = null,

    var content: String? = null
) {
    var userId: String? = null
}