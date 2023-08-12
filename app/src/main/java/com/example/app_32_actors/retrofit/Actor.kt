package com.example.app_32_actors.retrofit

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Actor(
    val actor : String,
    val image : String
)