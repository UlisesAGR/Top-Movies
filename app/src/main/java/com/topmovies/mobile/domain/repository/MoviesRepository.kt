/*
 * MoviesRepository.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.domain.repository

import com.topmovies.mobile.domain.model.movies.MovieModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getTopRatedMovies(): Flow<List<MovieModel>>
    suspend fun getMovieById(movieId: Int): Flow<MovieModel?>
}
