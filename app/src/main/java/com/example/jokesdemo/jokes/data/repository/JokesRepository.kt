package com.example.jokesdemo.jokes.data.repository

import android.util.Log
import com.example.jokesdemo.jokes.data.db.JokesDao
import com.example.jokesdemo.jokes.data.models.Jokes
import com.example.jokesdemo.jokes.data.network.JokesApi
import kotlinx.coroutines.flow.Flow

class JokesRepository(
    private val jokesApi : JokesApi,
    private val jokesDao : JokesDao
): JokesRepositorySource {


    private val TAG = JokesRepository::class.simpleName

    override suspend fun getJoke(): Jokes? {
        val jokeData = jokesApi.getJoke().body()
        Log.d(TAG, "data: $jokeData")
        if (jokeData != null) {
            jokeData.timestamp = System.currentTimeMillis()
            jokesDao.insertAll(jokeData)
        }
        return jokeData
    }

     override fun getJokeFromDB(): Flow<List<Jokes>> {
        return jokesDao.getAll()
    }
}