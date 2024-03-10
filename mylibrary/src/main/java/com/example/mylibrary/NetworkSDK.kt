package com.example.mylibrary

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkSDK(private val apiKey: String) {

    private val apiService: ApiService by lazy {
        createApiService()
    }

    private fun createApiService(): ApiService {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }

    suspend fun getPopularMovies(language: String, page: Int): MovieResponse {
        return apiService.getPopularMovies(language, page,apiKey)
    }

    suspend fun getTopRatedMovies(language: String, page: Int): MovieResponse {
        return apiService.getTopRatedMovies(language, page,apiKey)
    }
}