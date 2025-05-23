/*
 * MoviesRepositoryImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.data.repository

import com.topmovies.mobile.data.local.source.MoviesLocalSource
import com.topmovies.mobile.data.netwotk.source.MoviesNetworkSource
import com.topmovies.mobile.domain.mapper.toDomain
import com.topmovies.mobile.domain.mapper.toEntity
import com.topmovies.mobile.domain.model.movies.MovieModel
import com.topmovies.mobile.domain.repository.MoviesRepository
import com.topmovies.mobile.util.NetworkException
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

    override suspend fun getTopRatedMovies(): Flow<List<MovieModel>> = flow {
        val localMovies = moviesLocalSource.getMovies()
        if (localMovies.isNotEmpty()) {
            emit(localMovies.map { movie -> movie.toDomain() })
        } else {
            val response = moviesNetworkSource.getTopRatedMovies()
            if (response.isSuccessful) {
                val movies = response.body()?.results ?: emptyList()
                moviesLocalSource.insertAll(movies.map { movie -> movie.toEntity() })
                emit(movies.map { movie -> movie.toDomain() })
            } else {
                val error = response.errorBody()?.string()
                throw NetworkException(error)
            }
        }
    }.flowOn(dispatcher)

    override suspend fun getMovieById(movieId: Int): Flow<MovieModel?> = flow {
        val localMovie = moviesLocalSource.getMovieById(movieId)
        localMovie?.let {
            emit(localMovie.toDomain())
        } ?: run {
            val response = moviesNetworkSource.getMovieById(movieId)
            if (response.isSuccessful) {
                emit(response.body()?.toDomain())
            } else {
                val error = response.errorBody()?.string()
                throw NetworkException(error)
            }
        }
    }.flowOn(dispatcher)
}
