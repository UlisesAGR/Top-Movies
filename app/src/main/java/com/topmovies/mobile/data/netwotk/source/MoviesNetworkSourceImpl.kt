/*
 * MoviesNetworkSourceImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.data.netwotk.source

import com.topmovies.mobile.data.netwotk.model.MovieDataResponse
import com.topmovies.mobile.data.netwotk.model.MovieResponse
import com.topmovies.mobile.data.netwotk.service.MoviesService
import retrofit2.Response
import javax.inject.Inject

class MoviesNetworkSourceImpl @Inject constructor(
    private val moviesService: MoviesService,
) : MoviesNetworkSource {

    override suspend fun getTopRatedMovies(): Response<MovieDataResponse> =
        moviesService.getTopRatedMovies()

    override suspend fun getMovieById(movieId: Int): Response<MovieResponse?> =
        moviesService.getMovieById(movieId = movieId)
}
