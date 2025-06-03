/*
 * MoviesNetworkSourceImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.data.local.source

import com.topmovies.mobile.data.local.dao.MovieDao
import com.topmovies.mobile.domain.mapper.toDomain
import com.topmovies.mobile.domain.mapper.toEntity
import com.topmovies.mobile.domain.model.movies.MovieModel
import javax.inject.Inject

class MoviesLocalSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
) : MoviesLocalSource {

    override suspend fun insertAll(movies: List<MovieModel>) =
        movieDao.insertAll(movies.map { movie -> movie.toEntity() })

    override suspend fun getMovies(): List<MovieModel> =
        movieDao.getMovies().map { movie -> movie.toDomain() }

    override suspend fun getMovieById(movieId: Int): MovieModel? =
        movieDao.getMovieById(movieId)?.toDomain()
}
