/*
 * MoviesLocalSource.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.data.local.source

import com.topmovies.mobile.domain.model.movies.MovieModel

interface MoviesLocalSource {
    suspend fun insertAll(movies: List<MovieModel>)
    suspend fun getMovies(): List<MovieModel>
    suspend fun getMovieById(movieId: Int): MovieModel?
}
