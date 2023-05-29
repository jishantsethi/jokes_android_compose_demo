package com.example.jokesdemo.jokes.view.composablescreens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jokesdemo.jokes.data.models.Jokes
import com.example.jokesdemo.jokes.viewmodels.JokeViewModel
import com.example.jokesdemo.ui.theme.JokesDemoTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun JokesScreen(viewModel: JokeViewModel = koinViewModel(), modifier: Modifier = Modifier) {
    val homeUiState by viewModel.jokesUiState.collectAsState()
    LazyColumn(modifier = modifier) {
        itemsIndexed(
            items = homeUiState.itemList,
            key = {index, item: Jokes -> index.toString()+item.joke }
        ) { _, item: Jokes ->
           JokesItem(item, modifier)
        }
    }
}

@Preview
@Composable
fun JokesScreenPreview() {
    JokesDemoTheme(darkTheme = true, dynamicColor = false) {
        JokesItem(Jokes("Joke", id = 1, timestamp = 1L))
    }

}
