package com.example.jokesdemo.jokes.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Jokes(
    val joke: String,
    var timestamp: Long,
    @PrimaryKey(autoGenerate = true) val id: Int,
)
