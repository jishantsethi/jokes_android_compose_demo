package com.example.jokesdemo.jokes.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesdemo.jokes.data.models.Jokes
import com.example.jokesdemo.jokes.data.repository.JokesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


/*TODO:
   1. delete extra data FROM db when app starts
   2. add network check before calling api and time out via ok http
   3. progress bar while data is getting fetched
   4. Minor UI improvements
*/

class JokeViewModel(private val repository: JokesRepository) : ViewModel() {

    private val TAG = "JokeViewModel"

    private val scope = viewModelScope
    private var job: Job? = null

    /**
     * Holds home ui state. The list of items are retrieved from [JokesRepository] and mapped to
     * [JokesScreenUiState]
     */
    val jokesUiState: StateFlow<JokesScreenUiState> =
        repository.getJokeFromDB().map { JokesScreenUiState(it) }
            .stateIn(
                scope = scope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = JokesScreenUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    fun startUpdatesJokes() {
        stopUpdates()
        job = scope.launch() {
            while (true) {
                repository.getJoke()?.let {
                    Log.d(TAG, "joke data is non empty adding into state list")
                } ?: kotlin.run {
                    Log.e(TAG, "joke data is empty")
                }// the function that should be run every 1 minute
                delay(60 * 1000) // 1 minute
            }
        }
    }


    private fun stopUpdates() {
        job?.cancel()
        job = null
    }

    /**
     * clear job when view model is getting destroyed
     */
    override fun onCleared() {
        stopUpdates()
        super.onCleared()
    }
}

/**
 * Ui State for JokeScreen
 */
data class JokesScreenUiState(val itemList: List<Jokes> = listOf())