package com.anis.reddit.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.anis.reddit.api.RedditApi
import com.anis.reddit.models.Post
import com.anis.reddit.utils.Constants
import retrofit2.HttpException
import java.io.IOException


class RedditPagingSource(private val redditApi: RedditApi): PagingSource<String, Post>() {


    override fun getRefreshKey(state: PagingState<String, Post>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Post> {
        return try {
            val response = redditApi.fetchPosts(after = params.key, loadSize = Constants.PAGE_SIZE)
            val listing = response.data
            val redditPosts = listing.children.map { it.data }
            LoadResult.Page(
                redditPosts,
                listing.before,
                listing.after
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
    override val keyReuseSupported: Boolean = true
}