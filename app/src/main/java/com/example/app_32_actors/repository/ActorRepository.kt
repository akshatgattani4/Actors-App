package com.example.app_32_actors.repository

import com.example.app_32_actors.retrofit.Actor
import com.example.app_32_actors.retrofit.ActorAPI

class ActorRepository(private val actorAPI: ActorAPI) {
    suspend fun getActors() : List<Actor>{
        return actorAPI.getActors()
    }
}