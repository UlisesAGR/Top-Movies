/*
 * GetTopRatedMoviesUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.domain.usecase.movies

import com.topmovies.mobile.domain.model.movies.MovieModel
import com.topmovies.mobile.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
) {

    suspend operator fun invoke(): Flow<List<MovieModel>> =
        moviesRepository.getTopRatedMovies()
}
