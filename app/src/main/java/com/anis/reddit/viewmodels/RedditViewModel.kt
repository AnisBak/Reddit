package com.anis.reddit.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.anis.reddit.models.CommentsContainer
import com.anis.reddit.models.Post
import com.anis.reddit.repositories.RedditRepo

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private const val TAG = "RedditViewModel"

class RedditViewModel : ViewModel() {
    private val redditRepo = RedditRepo()

    var posts: Flow<PagingData<Post>> = emptyFlow()

    private val _comments: MutableStateFlow<List<CommentsContainer>> = MutableStateFlow(emptyList())
    val comments: StateFlow<List<CommentsContainer>>
        get() = _comments.asStateFlow()

    init {
        try {
            val items = redditRepo.fetchPosts(RedditRepo.getRetrofitInstance()).cachedIn(viewModelScope)
            Log.d(TAG, "Posts received: $items")
            posts = items
        } catch (ex: Exception) {
            Log.e(TAG, "Failed to fetch posts", ex)
        }

    }

    fun fetchComments(args: String){
        val length = args.length-2
        val commentUrl = args.substring(0..length)+".json"
        Log.d(TAG, commentUrl)
        viewModelScope.launch {
            try {
                val items = redditRepo.fetchComments(RedditRepo.getRetrofitInstance(),commentUrl)
                Log.d(TAG, "Comments received: $items")
                _comments.value = items
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch comments", ex)
            }
        }
    }
}
