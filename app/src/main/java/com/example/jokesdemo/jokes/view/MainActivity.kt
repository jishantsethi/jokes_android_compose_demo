package com.example.jokesdemo.jokes.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jokesdemo.R
import com.example.jokesdemo.jokes.view.composablescreens.JokesScreen
import com.example.jokesdemo.jokes.viewmodels.JokeViewModel
import com.example.jokesdemo.ui.theme.JokesDemoTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    private val jokeViewModel: JokeViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jokeViewModel.startUpdatesJokes()
        //jokeViewModel.getData()
        setContent {
            JokesDemoTheme {// "{" syntax of function is for last param in kotlin
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JokesScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, from: String = "Emma", modifier: Modifier = Modifier) {
    Box(
         modifier = modifier.padding(0.dp),
                 contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.androidparty),
            contentDescription = null,
            alpha = 0.9f,
            modifier = Modifier.background(color = Color.Black,),
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.Center, modifier = modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hello $name!",
                fontSize = 36.sp,
                lineHeight = 38.sp,
                modifier = modifier,
                textAlign = TextAlign.Center
            )
            Text(
                text = "$from!",
                fontSize = 20.sp,
                textAlign = TextAlign.Right,
                modifier = modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.End),
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JokesDemoTheme {
        Greeting("Android ki hal ne mara muka kan de thale das menu das menu")
    }
}