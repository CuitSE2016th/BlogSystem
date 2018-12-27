package com.lfork.blogsystem.articledetail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.lfork.blogsystem.R
import com.lfork.blogsystem.RandomTest
import com.lfork.blogsystem.data.comment.Comment
import kotlinx.android.synthetic.main.article_detail_comment_frag.view.*
import kotlinx.android.synthetic.main.item_comment.view.*

class ArticleCommentFragment : Fragment() {

    private lateinit var viewModel: ArticleDetailViewModel

    var root: View? = null

    var adapter: CommentAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (root == null) {
            root = inflater.inflate(R.layout.article_detail_comment_frag, container, false)
            adapter = CommentAdapter()
            root!!.recycle_comments.layoutManager = LinearLayoutManager(context)
            root!!.recycle_comments.adapter = adapter
        }
        return root!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ArticleDetailViewModel::class.java)

        // TODO: Use the ViewModel
    }

    inner class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {


        private val items = ArrayList<Comment>(10);

        init {
            val parentComment = items

            for (i in 0..9) {
                val c = Comment(
                    "$i",
                    "lfork${i % 2}",
                    "www.lfork/blog/images/1.png",
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

        }

        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CommentViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_comment, parent, false)
            return CommentViewHolder(view)
        }

        override fun getItemCount() = items.size


        override fun onBindViewHolder(p0: CommentViewHolder, p1: Int) {

        }


        inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val portrait: ImageView = itemView.comment_portrait
            val username: TextView = itemView.username
            val beRepliedUsername: TextView = itemView.be_replied_username
            val time: TextView = itemView.comment_time
            val content: TextView = itemView.content
            val btnDelete: Button = itemView.delete
            val btnLike = itemView.like
            val btnReply = itemView.reply
        }

    }

}
