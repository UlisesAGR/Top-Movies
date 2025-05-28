/*
 * MoviesNetworkSourceImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.data.netwotk.source

import com.topmovies.mobile.data.netwotk.model.MovieResponse
import com.topmovies.mobile.data.netwotk.service.MoviesService
import com.topmovies.mobile.utils.safe.Resource
import com.topmovies.mobile.utils.safe.toResource
import javax.inject.Inject

class MoviesNetworkSourceImpl @Inject constructor(
    private val moviesService: MoviesService,
) : MoviesNetworkSource {

    override suspend fun getTopRatedMovies(): Resource<List<MovieResponse>> =
        moviesService.getTopRatedMovies().toResource { results }

    override suspend fun getMovieById(movieId: Int): Resource<MovieResponse?> =
        moviesService.getMovieById(movieId = movieId).toResource()
}
