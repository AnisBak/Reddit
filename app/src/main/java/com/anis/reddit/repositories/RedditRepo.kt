package com.anis.reddit.repositories



import androidx.paging.*
import com.anis.reddit.api.RedditApi
import com.anis.reddit.models.CommentsContainer
import com.anis.reddit.models.Post
import com.anis.reddit.utils.Constants
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class RedditRepo {
    companion object {
        private var INSTANCE: RedditApi?=null
        fun initializeRetrofit() {
            if (INSTANCE == null) {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                INSTANCE = retrofit.create()
            }
        }
        fun getRetrofitInstance(): RedditApi {
            return INSTANCE ?: throw IllegalStateException("Retrofit instance not initialized")
        }
    }

    fun fetchPosts(redditApi:RedditApi): Flow<PagingData<Post>> {
        return Pager(PagingConfig(pageSize = Constants.PAGE_SIZE)){
            RedditPagingSource(redditApi)
        }.flow
    }


    suspend fun fetchComments(redditApi: RedditApi,commentUrl: String): List<CommentsContainer> = redditApi.fetchComments(commentUrl)[1].data.children
}
