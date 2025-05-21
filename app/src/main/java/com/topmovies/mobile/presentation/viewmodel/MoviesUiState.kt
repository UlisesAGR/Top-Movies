/*
 * MoviesUiState.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.presentation.viewmodel

import com.topmovies.mobile.domain.model.MovieModel

sealed class MoviesUiState {
    internal data class Loading(val isLoading: Boolean) : MoviesUiState()
    internal data class Error(val cause: Throwable?) : MoviesUiState()
    internal data class Movies(val movies: List<MovieModel>) : MoviesUiState()
    internal data class Movie(val movie: MovieModel?) : MoviesUiState()
}
