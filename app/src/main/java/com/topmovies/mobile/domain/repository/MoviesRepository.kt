/*
 * MoviesRepository.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.domain.repository

import com.topmovies.mobile.domain.model.movies.MovieModel
import com.topmovies.mobile.utils.extension.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getTopRatedMovies(): Flow<Resource<List<MovieModel>>>
    suspend fun getMovieById(movieId: Int): Flow<Resource<MovieModel?>>
}
