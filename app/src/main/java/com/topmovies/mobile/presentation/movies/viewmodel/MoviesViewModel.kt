/*
 * MoviesViewModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.presentation.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.topmovies.mobile.domain.usecase.movies.GetMovieByIdUseCase
import com.topmovies.mobile.domain.usecase.movies.GetTopRatedMoviesUseCase
import com.topmovies.mobile.util.Constants.MOVIES_COUNT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
) : ViewModel() {

    private var _moviesUiState = MutableSharedFlow<MoviesUiState>()
    val moviesUiState: SharedFlow<MoviesUiState> = _moviesUiState

    fun getTopRatedMovies() = viewModelScope.launch {
        _moviesUiState.emit(MoviesUiState.Loading(isLoading = true))
        getTopRatedMoviesUseCase()
            .catch { cause ->
                _moviesUiState.apply {
                    emit(MoviesUiState.ErrorMovies(cause))
                    emit(MoviesUiState.Loading(isLoading = false))
                }
            }
            .collect { movies ->
                _moviesUiState.apply {
                    emit(MoviesUiState.Movies(movies = movies.take(MOVIES_COUNT)))
                    emit(MoviesUiState.Loading(isLoading = false))
                }
            }
    }

    fun getMovieById(movieId: Int) = viewModelScope.launch {
        _moviesUiState.emit(MoviesUiState.Loading(isLoading = true))
        getMovieByIdUseCase(movieId)
            .catch { cause ->
                _moviesUiState.apply {
                    emit(MoviesUiState.ErrorMovie(cause))
                    emit(MoviesUiState.Loading(isLoading = false))
                }
            }
            .collect { movie ->
                _moviesUiState.apply {
                    emit(MoviesUiState.Movie(movie = movie))
                    emit(MoviesUiState.Loading(isLoading = false))
                }
            }
    }
}
