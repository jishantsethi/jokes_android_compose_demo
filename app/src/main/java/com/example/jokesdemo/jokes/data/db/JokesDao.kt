package com.example.jokesdemo.jokes.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.jokesdemo.jokes.data.models.Jokes
import kotlinx.coroutines.flow.Flow

@Dao
interface JokesDao {

    @Query("SELECT * FROM Jokes ORDER BY timestamp DESC LIMIT 10")
    fun getAll(): Flow<List<Jokes>>

    @Insert
    suspend fun insertAll(users: Jokes)

    @Delete
    suspend fun delete(user: Jokes)
}