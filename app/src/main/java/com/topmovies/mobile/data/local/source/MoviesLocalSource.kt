/*
 * MoviesLocalSource.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.data.local.source

import com.topmovies.mobile.data.local.model.MovieEntity

interface MoviesLocalSource {
    suspend fun insertAll(movies: List<MovieEntity>)
    suspend fun getMovies(): List<MovieEntity>
    suspend fun getMovieById(movieId: Int): MovieEntity?
}
