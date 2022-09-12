package com.anis.reddit.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Post(
    @Json(name = "thumbnail") val url: String,
    val title:String,
    val permalink:String
)
