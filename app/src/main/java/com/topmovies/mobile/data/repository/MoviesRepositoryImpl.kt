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
import com.topmovies.mobile.utils.extension.Resource
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
        emit(Resource.Loading)
        val localMovies = moviesLocalSource.getMovies()
        if (localMovies.isNotEmpty()) {
            emit(Resource.Success(localMovies.map { movie -> movie.toDomain() }))
        } else {
            val response = moviesNetworkSource.getTopRatedMovies()
            if (response.isSuccessful) {
                val movies = response.body()?.results ?: emptyList()
                moviesLocalSource.insertAll(movies.map { movie -> movie.toEntity() })
                emit(Resource.Success(movies.map { movie -> movie.toDomain() }))
            } else {
                val error = response.errorBody().toString()
                emit(Resource.Error(error))
            }
        }
    }.flowOn(dispatcher)

    override suspend fun getMovieById(movieId: Int): Flow<Resource<MovieModel?>> = flow {
        emit(Resource.Loading)
        val localMovie = moviesLocalSource.getMovieById(movieId)
        localMovie?.let {
            emit(Resource.Success(localMovie.toDomain()))
        } ?: run {
            val response = moviesNetworkSource.getMovieById(movieId)
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.toDomain()))
            } else {
                val error = response.errorBody().toString()
                emit(Resource.Error(error))
            }
        }
    }.flowOn(dispatcher)
}
