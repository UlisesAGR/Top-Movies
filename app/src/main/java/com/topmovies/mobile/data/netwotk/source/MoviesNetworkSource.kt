/*
 * MoviesNetworkSource.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.data.netwotk.source

import com.topmovies.mobile.domain.model.movies.MovieModel
import com.topmovies.mobile.utils.safe.Resource

interface MoviesNetworkSource {
    suspend fun getTopRatedMovies(): Resource<List<MovieModel>>
    suspend fun getMovieById(movieId: Int): Resource<MovieModel?>
}
