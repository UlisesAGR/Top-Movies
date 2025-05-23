/*
 * MoviesUiState.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.presentation.movies.viewmodel

import com.topmovies.mobile.domain.model.movies.MovieModel

sealed class MoviesUiState {
    internal data class Loading(val isLoading: Boolean) : MoviesUiState()
    internal data class Movies(val movies: List<MovieModel>) : MoviesUiState()
    internal data class Movie(val movie: MovieModel?) : MoviesUiState()
    internal data class ErrorGetTopRatedMovies(
        val cause: Throwable? = null,
        val message: String? = null,
    ) : MoviesUiState()
    internal data class ErrorGetMovieById(
        val cause: Throwable? = null,
        val message: String? = null,
    ) : MoviesUiState()
}
