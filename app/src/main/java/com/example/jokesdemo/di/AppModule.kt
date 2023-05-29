package com.example.jokesdemo.di

import android.app.Application
import androidx.room.Room
import com.example.jokesdemo.jokes.data.db.JokesDao
import com.example.jokesdemo.jokes.data.repository.JokesRepository
import com.example.jokesdemo.jokes.viewmodels.JokeViewModel
import com.example.jokesdemo.jokes.data.network.JokesApi
import com.example.jokesdemo.room.AppDatabase
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = "https://geek-jokes.sameerkumar.website"

val appModule = module {
    single { provideDatabase(get()) }
    single { provideJokesDao(get()) }
    single { createService(get()) }

    single { createRetrofit() }

    factory { JokesRepository(get(), get()) }
    viewModel { JokeViewModel(get()) }

    single { GsonConverterFactory.create() }
}

fun provideDatabase(androidApplication: Application): AppDatabase {
    return Room.databaseBuilder(
        androidApplication,
        AppDatabase::class.java,
        "database"
    ).build()
}

fun provideJokesDao(database: AppDatabase): JokesDao {
    return database.jokesDao()
}

/*fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}*/

fun createRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun createService(retrofit: Retrofit): JokesApi {
    return retrofit.create(JokesApi::class.java)
}