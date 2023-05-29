package com.example.jokesdemo.jokes.data.network

import com.example.jokesdemo.jokes.data.models.Jokes
import retrofit2.Response
import retrofit2.http.GET

interface JokesApi {
    @GET("/api?format=json")
    suspend fun getJoke() : Response<Jokes>
}