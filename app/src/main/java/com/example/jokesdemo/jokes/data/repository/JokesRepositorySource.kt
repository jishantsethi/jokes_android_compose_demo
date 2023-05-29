package com.example.jokesdemo.jokes.data.repository

import com.example.jokesdemo.jokes.data.models.Jokes
import kotlinx.coroutines.flow.Flow

interface JokesRepositorySource {
    suspend fun getJoke(): Jokes?
    fun getJokeFromDB(): Flow<List<Jokes>>
}