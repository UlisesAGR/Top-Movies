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
        _moviesUiState.emit(Loading(isLoading = true))
        getTopRatedMoviesUseCase()
            .catch { error ->
                _moviesUiState.apply {
                    emit(ErrorGetTopRatedMovies(message = errorMessageProvider.getUserMessage(error)))
                    emit(Loading(isLoading = false))
                }
            }
            .collect { response ->
                when (response) {
                    is Success -> {
                        _moviesUiState.apply {
                            emit(Movies(movies = response.data?.take(MOVIES_COUNT) ?: emptyList()))
                            emit(Loading(isLoading = false))
                        }
                    }
                    is Error -> {
                        _moviesUiState.apply {
                            emit(ErrorGetTopRatedMovies(message = response.message))
                            emit(Loading(isLoading = false))
                        }
                    }
                }
            }
    }

    fun getMovieById(movieId: Int) = viewModelScope.launch {
        _moviesUiState.emit(Loading(isLoading = true))
        getMovieByIdUseCase(movieId)
            .catch { error ->
                _moviesUiState.apply {
                    emit(ErrorGetMovieById(message = errorMessageProvider.getUserMessage(error)))
                    emit(Loading(isLoading = false))
                }
            }
            .collect { response ->
                when (response) {
                    is Success -> {
                        _moviesUiState.apply {
                            emit(Movie(movie = response.data))
                            emit(Loading(isLoading = false))
                        }
                    }
                    is Error -> {
                        _moviesUiState.apply {
                            emit(ErrorGetMovieById(message = response.message))
                            emit(Loading(isLoading = false))
                        }
                    }
                }
            }
    }
}
