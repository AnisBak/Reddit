package com.anis.reddit.models

import com.anis.reddit.models.Comment
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentsContainer(
    val data: Comment
)
