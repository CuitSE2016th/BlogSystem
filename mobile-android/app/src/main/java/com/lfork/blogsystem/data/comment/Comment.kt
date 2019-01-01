package com.lfork.blogsystem.data.comment

import com.lfork.blogsystem.common.Config

/**
 *
 * Created by 98620 on 2018/12/11.
 */
data class Comment(

    var id: String? = null,

    var username: String? = null,

    var portrait: String? = null,

    var authorId:String?=null,

    /**
     * 评论依赖所依赖的对象：文章、父评论
     */
    var parentId: String? = null,

    /**
     * 如果评论本身就是child，那么它就不会再有child了。只有两层评论
     */
    var children: ArrayList<Comment>? = null,


    var createTime: String? = null,

    /**
     * 回复对象的信息。 ui展示: username 回复了 replyTo
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

    fun getRealPortraitUrl(): String {
        if (portrait != null) {
            if (portrait!!.contains("http")) {
                return portrait!!
            }
        }
        return Config.ServerPath + portrait
    }
}