package com.anis.reddit.models

import com.anis.reddit.models.Post
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostContainer(
    val data: Post
)
