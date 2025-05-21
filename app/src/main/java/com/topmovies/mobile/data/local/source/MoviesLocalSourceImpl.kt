/*
 * MoviesNetworkSourceImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.data.local.source

import com.topmovies.mobile.data.local.dao.MovieDao
import com.topmovies.mobile.data.local.model.MovieEntity
import javax.inject.Inject

class MoviesLocalSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
) : MoviesLocalSource {

    override suspend fun insertAll(movies: List<MovieEntity>) =
        movieDao.insertAll(movies)

    override suspend fun getMovies(): List<MovieEntity> =
        movieDao.getMovies()

    override suspend fun getMovieById(movieId: Int): MovieEntity? =
        movieDao.getMovieById(movieId)
}
