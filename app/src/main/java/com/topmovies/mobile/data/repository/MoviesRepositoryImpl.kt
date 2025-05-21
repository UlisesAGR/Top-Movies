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
        if (localMovies.isEmpty()) {
            val response = moviesNetworkSource.getTopRatedMovies()
            if (response.isSuccessful) {
                val movies = response.body()?.results?.map { movie -> movie.toEntity() } ?: emptyList()
                moviesLocalSource.insertAll(movies)
                emit(moviesLocalSource.getMovies().map { movie -> movie.toDomain() })
            } else {
                emit(emptyList())
            }
        } else {
            emit(localMovies.map { movie -> movie.toDomain() })
        }
    }.flowOn(dispatcher)

    override suspend fun getMovieById(movieId: Int): Flow<MovieModel?> = flow {
        val localMovie = moviesLocalSource.getMovieById(movieId)
        if (localMovie == null) {
            val response = moviesNetworkSource.getMovieById(movieId)
            if (response.isSuccessful) {
                val movie = response.body()?.toDomain()
                emit(movie)
            } else {
                emit(null)
            }
        } else {
            emit(localMovie.toDomain())
        }
    }.flowOn(dispatcher)
}
