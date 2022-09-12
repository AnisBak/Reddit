package com.anis.reddit.models

import com.anis.reddit.models.CommentsListing
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentsResponse(
    val data: CommentsListing
)
