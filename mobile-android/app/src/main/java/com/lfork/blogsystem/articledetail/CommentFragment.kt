package com.lfork.blogsystem.articledetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.lfork.blogsystem.R
import com.lfork.blogsystem.base.databinding.ImageBinding.setImageNoCache
import com.lfork.blogsystem.data.comment.Comment
import com.lfork.blogsystem.utils.ToastUtil

import kotlinx.android.synthetic.main.article_detail_comment_frag.view.*
import kotlinx.android.synthetic.main.item_comment.view.*
import kotlinx.android.synthetic.main.item_comment_with_children.view.*

class CommentFragment : Fragment(), CommentNavigator {
    override fun addComment(c: Comment) {
        activity?.runOnUiThread {
            adapter?.addComment(c)
        }

    }

    override fun addSubComment(parent: Comment, child: Comment) {
        activity?.runOnUiThread {
            adapter?.addSubComment(parent, child)
        }
    }


    override fun addComments(comments: ArrayList<Comment>) {
        activity?.runOnUiThread {
            adapter?.addComments(comments)
        }

    }

    override fun refreshComments(comments: ArrayList<Comment>) {
        activity?.runOnUiThread {
            adapter?.refreshComments(comments)
        }

    }

    override fun showTips(msg: String?) {
        activity?.runOnUiThread {
            ToastUtil.showLong(context, msg);
        }
    }


    private var viewModel: ArticleDetailViewModel? = null

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
        if (viewModel == null) {
            viewModel = (activity as ArticleDetailActivity).viewModel!!
            viewModel?.commentNavigator = this
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.commentNavigator = null
    }

    /**
     * 按照时间来给评论排序。最新的评论放在前面
     */
    inner class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

        private val items = ArrayList<Comment>(10);

        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CommentViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_comment_with_children, parent, false)
            return CommentViewHolder(view)
        }

        override fun getItemCount() = items.size


        override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
            val item = items[position]

            if (item.children != null) {
                val params = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                for (i in item.children!!){
                    holder.subCommentContainer.addView(getSubItemView(item,holder), params)
                }
            } else {
                setImageNoCache( holder.portrait, item.userPortrait)
                holder.time.text = item.time
                holder.content.text = item.content
                holder.btnLike.text = "Like(${item.likeCount})"
                holder.username.text= item.userName
//

            }
        }

        private fun getSubItemView(item:Comment,holder: CommentViewHolder):View{

            val view = LayoutInflater.from(context)
                .inflate(R.layout.item_comment, holder.subCommentContainer, false)

            setImageNoCache( holder.portrait, item.userPortrait)
            view.comment_time.text = item.time
            view.content.text = item.content
            view.like.text = "Like(${item.likeCount})"
            view.username.text= item.userName
            view.be_replied_username.text = "Reply ${item.replyTo}:"
            view.reply.setOnClickListener {


            }

            return view
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
            val subCommentContainer = itemView.sub_comments
            val separator = itemView.separator
        }


        fun addComment(c: Comment) {
            items.add(0,c)
            notifyDataSetChanged()
        }

        fun addSubComment(parent: Comment, child: Comment) {
            if (parent.children == null) {
                parent.children = ArrayList()
            }
            parent.children?.add(child)
            notifyDataSetChanged()
        }


        /**
         * 只有首次刷新
         */
        fun refreshComments(comments: ArrayList<Comment>) {
            items.clear()
            items.addAll(comments)
            notifyDataSetChanged()
        }

        /**
         * 只有加载更多
         */
        fun addComments(comments: ArrayList<Comment>) {
            items.addAll(comments)
            notifyDataSetChanged()
        }


    }

}
