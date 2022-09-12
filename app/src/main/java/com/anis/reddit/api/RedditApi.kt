package com.anis.reddit.api

import com.anis.reddit.models.CommentsResponse
import com.anis.reddit.models.PostsResponse
import com.anis.reddit.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface RedditApi {
    @GET(Constants.END_POINT)
    suspend fun fetchPosts(@Query("limit") loadSize: Int = 0,
                           @Query("after") after: String? = null,
                           @Query("before") before: String? = null): PostsResponse

    @GET
    suspend fun fetchComments(@Url url:String,
                              @Query("limit") loadSize: Int = 0,
                              @Query("after") after: String? = null,
                              @Query("before") before: String? = null): List<CommentsResponse>
}
