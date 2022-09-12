package com.anis.reddit.models

import com.anis.reddit.models.PostContainer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostsListing(
    @Json(name = "children") val children: List<PostContainer>,
    val after: String?=null,
    val before: String?=null
)
