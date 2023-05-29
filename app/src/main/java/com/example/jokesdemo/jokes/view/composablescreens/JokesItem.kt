package com.example.jokesdemo.jokes.view.composablescreens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jokesdemo.jokes.data.models.Jokes

@Composable
fun JokesItem(joke: Jokes, modifier: Modifier = Modifier) {
    Text(
        text = joke.joke,
        fontSize = 20.sp,
        modifier = modifier.padding(8.dp).background(color = MaterialTheme.colorScheme.inversePrimary),
        textAlign = TextAlign.Center,
    )
}
