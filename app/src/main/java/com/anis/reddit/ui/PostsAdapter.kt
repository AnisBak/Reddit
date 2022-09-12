package com.anis.reddit.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.anis.reddit.R
import com.anis.reddit.databinding.PostItemBinding
import com.anis.reddit.models.Post
import com.anis.reddit.utils.DiffUtilCallBack


class RedditPostViewHolder(private val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post, onPostClicked: (String) -> Unit) {
        binding.apply {
            root.setOnClickListener{
                onPostClicked(post.permalink)
            }
            title.text = post.title
            if (!URLUtil.isValidUrl(post.url)){
                itemImageView.load(R.drawable.ic_sharp_android_24)
            }else{
                itemImageView.load(post.url)
            }
        }
    }
}

class RedditPostAdapter(private val onPostClicked: (String) -> Unit) :
    PagingDataAdapter<Post, RedditPostViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditPostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostItemBinding.inflate(inflater, parent, false)
        return RedditPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RedditPostViewHolder, position: Int) {
        getItem(position)?.let { redditPost ->
            holder.bind(redditPost,onPostClicked)
        }
    }
}
