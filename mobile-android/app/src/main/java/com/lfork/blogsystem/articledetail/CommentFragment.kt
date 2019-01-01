package com.lfork.blogsystem.articledetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.lfork.blogsystem.BlogApplication

import com.lfork.blogsystem.R
import com.lfork.blogsystem.RandomTest
import com.lfork.blogsystem.base.databinding.ImageBinding.setImage
import com.lfork.blogsystem.base.databinding.ImageBinding.setImageNoCache
import com.lfork.blogsystem.base.widget.listener.BottomListener
import com.lfork.blogsystem.base.widget.listener.RecyclerViewScrollListener
import com.lfork.blogsystem.data.comment.Comment
import com.lfork.blogsystem.data.user.UserDataRepository
import com.lfork.blogsystem.utils.TimeUtil
import com.lfork.blogsystem.utils.ToastUtil
import com.lfork.blogsystem.utils.hideKeyboard
import kotlinx.android.synthetic.main.article_detail_comment_frag.*

import kotlinx.android.synthetic.main.article_detail_comment_frag.view.*
import kotlinx.android.synthetic.main.item_comment.view.*
import kotlinx.android.synthetic.main.item_comment_parent.view.*

class CommentFragment : Fragment(), CommentNavigator, BottomListener {
    override fun onScrollToBottom() {
        if (root?.text_no_data_tips?.visibility != View.VISIBLE){
            viewModel?.loadMoreComments()
        }
    }

    override fun loadMoreComments(comments: ArrayList<Comment>) {
        if (comments.size>0){
            adapter?.loadMoreComments(comments)
        } else{
            root?.text_no_data_tips?.visibility = View.VISIBLE
        }

    }

    override fun deleteComment(position: Int,c: Comment) {
        adapter?.deleteComment(position,c)
    }

    override fun addComment(c: Comment) {
        activity?.runOnUiThread {
            recycle_comments.visibility = View.VISIBLE
            adapter?.addComment(c)
        }

    }

    override fun addSubComment(position: Int, parent: Comment, child: Comment) {
        activity?.runOnUiThread {
            adapter?.addSubComment(position, parent, child)
        }
    }


    override fun addComments(comments: ArrayList<Comment>) {
        activity?.runOnUiThread {
            recycle_comments.visibility = View.VISIBLE
            adapter?.addComments(comments)
        }

    }

    override fun refreshComments(comments: ArrayList<Comment>) {
        activity?.runOnUiThread {
            if (comments.size > 0) {
                recycle_comments.visibility = View.VISIBLE
                adapter?.refreshComments(comments)
            } else {
                recycle_comments.visibility = View.GONE
            }

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
            root!!.recycle_comments.isNestedScrollingEnabled = false;
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


    private fun openReplyDialog(position: Int, parent: Comment) {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        val builder = AlertDialog.Builder(context!!)

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setTitle("Reply to ${parent.username}")

        val inputView = EditText(context)
        builder.setView(inputView)

//        inputView.setText(viewModel.username.get())

        // Add the buttons
        builder.setPositiveButton(R.string.ok) { _, id ->
            val content = inputView.text.toString()
            if (TextUtils.isEmpty(content)) {
                ToastUtil.showShort(context!!, "Content cannot be null.")

            } else {
                val c = Comment()
                c.content = content
                c.replyTo = parent.username
                c.createTime = System.currentTimeMillis().toString()
                c.parentId = parent.id
                c.authorId = UserDataRepository.userCache.id
                c.userId = UserDataRepository.userCache.id
                c.username = UserDataRepository.userCache.getUsername()
                c.portrait = UserDataRepository.userCache.headPortrait
                viewModel?.addSubComment(position, parent, c)
            }
        }
        //do nothing
        builder.setNegativeButton(R.string.cancel) { dialog, id -> }
        // 3. Get the AlertDialog from create()
        val dialog = builder.create()
        dialog.show()
    }

    /**
     * 按照时间来给评论排序。最新的评论放在前面
     */
    inner class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

        private val items = ArrayList<Comment>(10);

        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CommentViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_comment_parent, parent, false)
            return CommentViewHolder(view)
        }

        override fun getItemCount() = items.size


        override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
            val item = items[position]
            if (item.children != null && item.children!!.size > 0) {


                val params = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)

                holder.subCommentContainer.visibility = View.VISIBLE
                holder.subCommentContainer.removeAllViews()
                val childrenList = forestToArrayList(item.children!!)

                childrenList.sortBy { comment -> comment.createTime }
                for (c in childrenList) {
                    holder.subCommentContainer.addView(
                        getSubItemView(position, item, c, holder),
                        params
                    )
                }
            } else {
                holder.subCommentContainer.visibility = View.GONE
            }


            setImageNoCache(holder.portrait, item.portrait ?: RandomTest.getRandomImages())

            holder.time.text = TimeUtil.getStandardTime("${(item.createTime ?: 0)}".toLong())
            holder.content.text = item.content
//            holder.btnLike.text = "Like(${item.likeCount?:0})"
            if (item.replyTo != null) {
                holder.beRepliedUsername.text = "Reply ${item.replyTo}:"
            } else {
                holder.beRepliedUsername.text = ""
            }
            holder.username.text = item.username
            holder.btnReply.setOnClickListener {
                openReplyDialog(position, item)
            }

            if ((item.authorId != null && (item.authorId == BlogApplication.userId)) || item.portrait==UserDataRepository.userCache.headPortrait){
                holder.btnDelete.visibility = View.VISIBLE
                holder.btnDelete.setOnClickListener {
                    viewModel?.deleteComment(position,item)
                }
            } else{
                holder.btnDelete.visibility = View.GONE
            }

            if (position == items.size - 1){
                holder.separator.visibility = View.GONE
            } else{
                holder.separator.visibility = View.VISIBLE
            }

//            holder.btn_reply_ok.setOnClickListener {
//                val content = holder.edit_reply_content.text
//                if (TextUtils.isEmpty(content)) {
//                    ToastUtil.showShort(context, "Content cannot be null.")
//                } else {
//                    holder.reply_layout.visibility = View.GONE
//                    val c = Comment(content = content.toString())
//                    c.replyTo = item.username
//                    viewModel?.addSubComment(item, c)
//                    activity?.hideKeyboard()
//                }
//            }



        }

        private fun forestToArrayList(children: ArrayList<Comment>): ArrayList<Comment> {

            val moreChildren = ArrayList<Comment>()

            for (c in children) {
                if (c.children != null && c.children!!.size > 0) {
                    moreChildren.addAll(forestToArrayList(c.children!!))
                }
            }

            moreChildren.addAll(0,children)
            return moreChildren
        }

        private fun getSubItemView(
            position: Int,
            parent: Comment,
            child: Comment,
            holder: CommentViewHolder
        ): View {

            val view = LayoutInflater.from(context)
                .inflate(R.layout.item_comment_child, holder.subCommentContainer, false)

            setImage(view.comment_portrait, child.portrait ?: RandomTest.getRandomImages())
            view.comment_time.text = TimeUtil.getStandardTime("${(child.createTime ?: 0)}".toLong())
            view.content.text = child.content
//            view.like.text = "Like(${child.likeCount?:0})"
            view.username.text = child.username
            view.be_replied_username.text = "Reply ${child.replyTo ?: parent.username}:"

            view.reply.setOnClickListener {
                openReplyDialog(position, child)
//                if (view.reply_layout.visibility == View.VISIBLE) {
//                    view.reply.text = "Reply"
//                    view.reply_layout.visibility = View.GONE
//                } else {
//                    view.reply.text = "Cancel"
//                    view.reply_layout.visibility = View.VISIBLE
//                }
            }

//            view.btn_reply_ok.setOnClickListener {
//                val content = view.editText_reply_content.text
//                if (TextUtils.isEmpty(content)) {
//                    ToastUtil.showShort(context, "Content cannot be null.")
//                } else {
//                    view.reply_layout.visibility = View.GONE
//                    val c = Comment(content = content.toString())
//                    c.replyTo = child.username
//                    viewModel?.addSubComment(child, c)
//                    activity?.hideKeyboard()
//                }
//            }
            return view
        }


        inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val portrait: ImageView = itemView.comment_portrait
            val username: TextView = itemView.username
            val beRepliedUsername: TextView = itemView.be_replied_username
            val time: TextView = itemView.comment_time
            val content: TextView = itemView.content
            val btnLike = itemView.like
            val btnDelete = itemView.btn_delete
            val btnReply = itemView.reply
            val subCommentContainer = itemView.sub_comments
            val reply_layout = itemView.reply_layout
            val edit_reply_content = itemView.editText_reply_content
            val btn_reply_ok = itemView.btn_reply_ok
            val separator = itemView.separator
        }


        fun addComment(c: Comment) {
            items.add(0, c)
            notifyItemInserted(0)
        }

        fun addSubComment(position: Int, parent: Comment, child: Comment) {

//            addComment(child)
            if (parent.children == null) {
                parent.children = ArrayList()
            }
            parent.children?.add(child)
            notifyItemChanged(position)
        }

        fun deleteComment(position: Int,c: Comment) {
            items.remove(c)
            notifyItemRemoved(position)
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

        fun loadMoreComments(comments: ArrayList<Comment>) {
            val tailIndex = items.size - 1
            items.addAll(comments)
            notifyItemRangeChanged(tailIndex, comments.size)
        }
    }
}
