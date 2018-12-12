package com.lfork.blogsystem.follow

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
import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.R
import com.lfork.blogsystem.base.databinding.ImageBinding
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.user.User
import com.lfork.blogsystem.data.user.UserDataRepository
import com.lfork.blogsystem.usermainpage.UserMainPageActivity
import com.lfork.blogsystem.utils.ToastUtil
import kotlinx.android.synthetic.main.following_frag.view.*
import kotlinx.android.synthetic.main.item_following.view.*

class FollowingFragment : Fragment() {

    companion object {
        fun newInstance() = FollowingFragment()
    }

    private lateinit var viewModel: FollowingViewModel

    private lateinit var adapter: FollowingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.following_frag, container, false)
        root.recycle_following.layoutManager = LinearLayoutManager(context)
        adapter = FollowingAdapter()
        root.recycle_following.adapter = adapter
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FollowingViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onResume() {
        super.onResume()
        val callback = object : DataCallback<List<User>> {
            override fun succeed(data: List<User>) {
                adapter.refreshItems(data as ArrayList<User>)
            }

            override fun failed(code: Int, log: String) {
                ToastUtil.showShort(context, log)
            }
        }

        UserDataRepository.getFollowings(
            UserDataRepository.userCache.getAccount(),
            BlogApplication.token!!,
            callback
        )
    }

    private fun unFollow(followButton: Button, beUnFollowedUser: User) {
        val callback = object : DataCallback<String> {
            override fun succeed(data: String) {
                beUnFollowedUser.beFollowed = false
                followButton.text = resources.getText(R.string.follow)
            }

            override fun failed(code: Int, log: String) {
                ToastUtil.showShort(context, log)
            }
        }

        UserDataRepository.unFollow(
            beUnFollowedUser.getAccount(),
            UserDataRepository.userCache.getAccount(),
            BlogApplication.token!!,
            callback)

    }

    private fun follow(followButton: Button, beFollowedUser: User) {
        val callback = object : DataCallback<String> {
            override fun succeed(data: String) {
                beFollowedUser.beFollowed = true
                followButton.text = resources.getText(R.string.unfollow)
            }

            override fun failed(code: Int, log: String) {
                ToastUtil.showShort(context, log)
            }
        }

        UserDataRepository.unFollow(
            beFollowedUser.getAccount(),
            UserDataRepository.userCache.getAccount(),
            BlogApplication.token!!,
            callback)

    }


    inner class FollowingAdapter() :
        RecyclerView.Adapter<FollowingAdapter.MyViewHolder>() {
        val items = ArrayList<User>(0);


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_following, parent, false)
            val holder = MyViewHolder(view)

            return holder
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
            holder.description.text = item.description

            holder.itemView.setOnClickListener {
                if (context != null) {
                    UserMainPageActivity.startActivity(context!!, item.getAccount())
                }
            }

            if(item.beFollowed){
                holder.followButton.text = resources.getText(R.string.unfollow)
            } else {
                holder.followButton.text = resources.getText(R.string.follow)
            }

            holder.followButton.setOnClickListener {
                if(item.beFollowed){
                    unFollow(holder.followButton,item)

                } else {
                    follow(holder.followButton,item)

                }
            }
        }


        fun refreshItems(arrayList: ArrayList<User>) {
            this.items.clear()
            this.items.addAll(arrayList)
            notifyDataSetChanged()
        }

        inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val username: TextView = itemView.username
            val portrait: ImageView = itemView.user_portrait
            val description: TextView = itemView.user_info_tips
            val followButton: Button = itemView.btn_follow
        }

    }

}
