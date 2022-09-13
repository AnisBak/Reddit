package com.anis.reddit.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anis.reddit.databinding.CommentItemBinding
import com.anis.reddit.models.CommentsContainer

class RedditCommentsViewHolder(
    private val binding: CommentItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(commentContainer: CommentsContainer) {
        binding.comment.text = commentContainer.data.body
    }
}

class RedditCommentAdapter(
    private val galleryItems: List<CommentsContainer>
) : RecyclerView.Adapter<RedditCommentsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RedditCommentsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CommentItemBinding.inflate(inflater, parent, false)
        return RedditCommentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RedditCommentsViewHolder, position: Int) {
        val item = galleryItems[position]
        holder.bind(item)
    }

    override fun getItemCount() = galleryItems.size-1
}