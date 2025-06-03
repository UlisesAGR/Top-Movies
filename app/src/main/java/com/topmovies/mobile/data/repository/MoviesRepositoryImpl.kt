/*
 * MoviesRepositoryImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.data.repository

import com.topmovies.mobile.data.local.source.MoviesLocalSource
import com.topmovies.mobile.data.netwotk.source.MoviesNetworkSource
import com.topmovies.mobile.domain.model.movies.MovieModel
import com.topmovies.mobile.domain.repository.MoviesRepository
import com.topmovies.mobile.utils.safe.Resource
import com.topmovies.mobile.utils.safe.Resource.Error
import com.topmovies.mobile.utils.safe.Resource.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesNetworkSource: MoviesNetworkSource,
    private val moviesLocalSource: MoviesLocalSource,
    private val dispatcher: CoroutineDispatcher,
) : MoviesRepository {

    override suspend fun getTopRatedMovies(): Flow<Resource<List<MovieModel>>> = flow {
        val localMovies = moviesLocalSource.getMovies()
        if (localMovies.isNotEmpty()) {
            emit(Success(localMovies))
        } else {
            when (val response = moviesNetworkSource.getTopRatedMovies()) {
                is Success -> {
                    val movies = response.data ?: emptyList()
                    moviesLocalSource.insertAll(movies)
                    emit(Success(movies))
                }
                is Error -> {
                    emit(Error(response.code, response.message))
                }
            }
        }
    }.flowOn(dispatcher)

    override suspend fun getMovieById(movieId: Int): Flow<Resource<MovieModel?>> = flow {
        val localMovie = moviesLocalSource.getMovieById(movieId)
        if (localMovie != null) {
            emit(Success(localMovie))
        } else {
            when (val response = moviesNetworkSource.getMovieById(movieId)) {
                is Success -> {
                    val movie = response.data
                    emit(Success(movie))
                }
                is Error -> {
                    emit(Error(response.code, response.message))
                }
            }
        }
    }.flowOn(dispatcher)
}
