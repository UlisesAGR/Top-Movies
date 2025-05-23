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
import com.topmovies.mobile.utils.extension.Resource
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
        getTopRatedMoviesUseCase()
            .catch { error ->
                _moviesUiState.apply {
                    emit(MoviesUiState.ErrorGetTopRatedMovies(cause = error))
                    emit(MoviesUiState.Loading(isLoading = false))
                }
            }
            .collect { response ->
                when (response) {
                    is Resource.Loading -> {
                        _moviesUiState.emit(MoviesUiState.Loading(isLoading = true))
                    }
                    is Resource.Success -> {
                        _moviesUiState.apply {
                            emit(MoviesUiState.Movies(movies = response.data.take(MOVIES_COUNT)))
                            emit(MoviesUiState.Loading(isLoading = false))
                        }
                    }
                    is Resource.Error -> {
                        _moviesUiState.apply {
                            emit(MoviesUiState.ErrorGetTopRatedMovies(message = response.message))
                            emit(MoviesUiState.Loading(isLoading = false))
                        }
                    }
                }
            }
    }

    fun getMovieById(movieId: Int) = viewModelScope.launch {
        getMovieByIdUseCase(movieId)
            .catch { error ->
                _moviesUiState.apply {
                    emit(MoviesUiState.ErrorGetMovieById(cause = error))
                    emit(MoviesUiState.Loading(isLoading = false))
                }
            }
            .collect { response ->
                when (response) {
                    is Resource.Loading -> {
                        _moviesUiState.emit(MoviesUiState.Loading(isLoading = true))
                    }
                    is Resource.Success -> {
                        _moviesUiState.apply {
                            emit(MoviesUiState.Movie(movie = response.data))
                            emit(MoviesUiState.Loading(isLoading = false))
                        }
                    }
                    is Resource.Error -> {
                        _moviesUiState.apply {
                            emit(MoviesUiState.ErrorGetMovieById(message = response.message))
                            emit(MoviesUiState.Loading(isLoading = false))
                        }
                    }
                }
            }
    }
}
