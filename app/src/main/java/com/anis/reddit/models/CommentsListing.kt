package com.anis.reddit.models

import com.anis.reddit.models.CommentsContainer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentsListing(
    @Json(name = "children") val children: List<CommentsContainer>
)
