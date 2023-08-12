package com.example.app_32_actors.retrofit

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    fun provideAPI(builder: Retrofit.Builder): ActorAPI {
        return builder.build().create(ActorAPI::class.java)
    }

    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://hp-api.onrender.com/api/")
            .addConverterFactory(MoshiConverterFactory.create())
    }

}