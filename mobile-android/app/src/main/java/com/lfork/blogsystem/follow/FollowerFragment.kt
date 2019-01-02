package com.lfork.blogsystem.follow

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.R
import com.lfork.blogsystem.base.databinding.ImageBinding
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.user.User
import com.lfork.blogsystem.data.user.UserDataRepository
import com.lfork.blogsystem.usermainpage.UserMainPageActivity
import com.lfork.blogsystem.utils.ToastUtil
import kotlinx.android.synthetic.main.follower_frag.view.*
import kotlinx.android.synthetic.main.item_follower.view.*

class FollowerFragment : Fragment() {

    companion object {
        fun newInstance() = FollowerFragment()
    }

    private lateinit var viewModel: FollowerViewModel

    private lateinit var adapter: FollowerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val root = inflater.inflate(R.layout.follower_frag, container, false)
        root.recycle_followers.layoutManager = LinearLayoutManager(context)
        adapter = FollowerAdapter()
        root.recycle_followers.adapter = adapter
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FollowerViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        //加载数据

        val callback = object:DataCallback<ArrayList<User>>{
            override fun succeed(data: ArrayList<User>) {
               adapter.refreshItems(data)
            }

            override fun failed(code: Int, log: String) {
                ToastUtil.showShort(context, log)
            }
        }

        UserDataRepository.getFollowers(BlogApplication.token!!, callback)

    }

    inner class FollowerAdapter() :
        RecyclerView.Adapter<FollowerAdapter.MyViewHolder>() {
        private val items = ArrayList<User>(0);

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_follower, parent, false)
            return MyViewHolder(view)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, p1: Int) {
            val item = items[p1]
            holder.username.text = item.getUsername()
            ImageBinding.setImage(
                holder.portrait,
                item.getRealPortraitUrl(),
                R.drawable.ic_person_black_24dp
            )
            //暂时没有description
            holder.description.text = item.description?:"The user has no self description."

            holder.itemView.setOnClickListener {
                if (context != null) {
                    UserMainPageActivity.startActivity(context!!, item.getAccount())
                }
            }
        }

        fun refreshItems(items: ArrayList<User>) {
            this.items.clear()
            this.items.addAll(items)
            notifyDataSetChanged()
        }

        inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val username: TextView = itemView.username
            val portrait: ImageView = itemView.user_portrait
            val description: TextView = itemView.user_info_tips
        }
    }

}
