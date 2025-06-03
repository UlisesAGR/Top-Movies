/*
 * MoviesNetworkSourceImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.data.netwotk.source

import com.topmovies.mobile.data.netwotk.service.MoviesService
import com.topmovies.mobile.domain.mapper.toDomain
import com.topmovies.mobile.domain.model.movies.MovieModel
import com.topmovies.mobile.utils.safe.Resource
import com.topmovies.mobile.utils.safe.toResource
import javax.inject.Inject

class MoviesNetworkSourceImpl @Inject constructor(
    private val moviesService: MoviesService,
) : MoviesNetworkSource {

    override suspend fun getTopRatedMovies(): Resource<List<MovieModel>> =
        moviesService.getTopRatedMovies().toResource {
            results.map { movie -> movie.toDomain() }
        }

    override suspend fun getMovieById(movieId: Int): Resource<MovieModel?> =
        moviesService.getMovieById(movieId = movieId).toResource {
            this?.toDomain()
        }
}
