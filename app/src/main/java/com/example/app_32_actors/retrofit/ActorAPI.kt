package com.example.app_32_actors.retrofit

import retrofit2.http.GET

interface ActorAPI {
    @GET("characters")
    suspend fun getActors() : List<Actor>
}