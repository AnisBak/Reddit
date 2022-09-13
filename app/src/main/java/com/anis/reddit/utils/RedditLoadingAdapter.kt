
package com.anis.reddit.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anis.reddit.databinding.ItemLoadingStateBinding


class LoadingStateViewHolder(private val binding: ItemLoadingStateBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindState(loadState: LoadState) {
        binding.progressBar.isVisible = loadState is LoadState.Loading
    }
}

class RedditLoadingAdapter :
    LoadStateAdapter<LoadingStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bindState(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadingStateViewHolder {

        val inflater = LayoutInflater.from(parent.context)
            val binding = ItemLoadingStateBinding.inflate(inflater, parent, false)
        return LoadingStateViewHolder(binding)
    }
}