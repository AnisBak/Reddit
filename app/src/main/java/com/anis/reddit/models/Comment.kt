package com.anis.reddit.models


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Comment(
     val body:String =""
)
