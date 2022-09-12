
package com.anis.reddit.utils

import androidx.recyclerview.widget.DiffUtil
import com.anis.reddit.models.Post

class DiffUtilCallBack : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.title == newItem.title
                && oldItem.url == newItem.url
                && oldItem.permalink == newItem.permalink
    }
}