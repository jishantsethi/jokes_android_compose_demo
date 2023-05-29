package com.example.jokesdemo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jokesdemo.jokes.data.db.JokesDao
import com.example.jokesdemo.jokes.data.models.Jokes

@Database(entities = [Jokes::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun jokesDao(): JokesDao
}
