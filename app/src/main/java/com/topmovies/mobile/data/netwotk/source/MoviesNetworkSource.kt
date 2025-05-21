/*
 * MoviesNetworkSource.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.data.netwotk.source

import com.topmovies.mobile.data.netwotk.model.MovieDataResponse
import com.topmovies.mobile.data.netwotk.model.MovieResponse
import retrofit2.Response

interface MoviesNetworkSource {
    suspend fun getTopRatedMovies(): Response<MovieDataResponse>
    suspend fun getMovieById(movieId: Int): Response<MovieResponse?>
}
