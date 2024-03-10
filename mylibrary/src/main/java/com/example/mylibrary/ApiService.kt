package com.example.mylibrary


import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    @Headers("accept: application/json")
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Header("Authorization") authorization: String = Util.header
    ): MovieResponse

    @GET("movie/top_rated")
    @Headers("accept: application/json")
    suspend fun getTopRatedMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Header("Authorization") authorization: String = Util.header
    ): MovieResponse
}