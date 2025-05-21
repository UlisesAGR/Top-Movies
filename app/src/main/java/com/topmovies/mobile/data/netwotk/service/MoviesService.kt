/*
 * MoviesService.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.data.netwotk.service

import com.topmovies.mobile.BuildConfig.TOP_RATED_MOVIES_ENDPOINT
import com.topmovies.mobile.data.netwotk.model.MovieDataResponse
import com.topmovies.mobile.data.netwotk.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET(TOP_RATED_MOVIES_ENDPOINT)
    suspend fun getTopRatedMovies(
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1,
    ): Response<MovieDataResponse>

    @GET("{movie_id}")
    suspend fun getMovieById(
        @Query("language") language: String = "es-ES",
        @Path("movie_id") movieId: Int,
    ): Response<MovieResponse?>
}
