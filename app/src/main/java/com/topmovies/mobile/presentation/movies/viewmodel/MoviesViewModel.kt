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
import com.topmovies.mobile.presentation.movies.viewmodel.MoviesUiState.ErrorGetMovieById
import com.topmovies.mobile.presentation.movies.viewmodel.MoviesUiState.ErrorGetTopRatedMovies
import com.topmovies.mobile.presentation.movies.viewmodel.MoviesUiState.Loading
import com.topmovies.mobile.presentation.movies.viewmodel.MoviesUiState.Movie
import com.topmovies.mobile.presentation.movies.viewmodel.MoviesUiState.Movies
import com.topmovies.mobile.util.Constants.MOVIES_COUNT
import com.topmovies.mobile.util.provider.ErrorMessageProvider
import com.topmovies.mobile.utils.safe.Resource.Error
import com.topmovies.mobile.utils.safe.Resource.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val errorMessageProvider: ErrorMessageProvider,
) : ViewModel() {

    private var _moviesUiState = MutableSharedFlow<MoviesUiState>()
    val moviesUiState: SharedFlow<MoviesUiState> = _moviesUiState

    fun getTopRatedMovies() = viewModelScope.launch {
        getTopRatedMoviesUseCase()
            .onStart { _moviesUiState.emit(Loading(isLoading = true)) }
            .onCompletion { _moviesUiState.emit(Loading(isLoading = false)) }
            .catch { error ->
                _moviesUiState.emit(ErrorGetTopRatedMovies(message = errorMessageProvider.getMessage(error)))
            }
            .collect { response ->
                when (response) {
                    is Success -> {
                        _moviesUiState.emit(Movies(movies = response.data?.take(MOVIES_COUNT) ?: emptyList()))
                    }
                    is Error -> {
                        _moviesUiState.emit(ErrorGetTopRatedMovies(message = response.message))
                    }
                }
            }
    }

    fun getMovieById(movieId: Int) = viewModelScope.launch {
        getMovieByIdUseCase(movieId)
            .onStart { _moviesUiState.emit(Loading(isLoading = true)) }
            .onCompletion { _moviesUiState.emit(Loading(isLoading = false)) }
            .catch { error ->
                _moviesUiState.emit(ErrorGetMovieById(message = errorMessageProvider.getMessage(error)))
            }
            .collect { response ->
                when (response) {
                    is Success -> {
                        _moviesUiState.emit(Movie(movie = response.data))
                    }
                    is Error -> {
                        _moviesUiState.emit(ErrorGetMovieById(message = response.message))
                    }
                }
            }
    }
}
