/*
 * MoviesViewModelTest.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.presentation.viewmodel

import app.cash.turbine.test
import com.topmovies.mobile.domain.usecase.movies.GetMovieByIdUseCase
import com.topmovies.mobile.domain.usecase.movies.GetTopRatedMoviesUseCase
import com.topmovies.mobile.presentation.movies.viewmodel.MoviesUiState
import com.topmovies.mobile.presentation.movies.viewmodel.MoviesViewModel
import com.topmovies.mobile.util.Constants.MOVIES_COUNT
import com.topmovies.mobile.utils.DispatcherRule
import com.topmovies.mobile.utils.MovieMock.movieModel
import com.topmovies.mobile.utils.MovieMock.movieModelFlow
import com.topmovies.mobile.utils.MovieMock.moviesModel
import com.topmovies.mobile.utils.MovieMock.moviesModelFlow
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
@HiltAndroidTest
class MoviesViewModelTest {

    private lateinit var moviesViewModel: MoviesViewModel

    @Mock
    lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase

    @Mock
    lateinit var getMovieByIdUseCase: GetMovieByIdUseCase

    @get:Rule
    val dispatcherRule = DispatcherRule()

    @Before
    fun setUp() {
        moviesViewModel = MoviesViewModel(
            getTopRatedMoviesUseCase,
            getMovieByIdUseCase,
        )
    }

    @Test
    fun `Get Top Rated Movies From Use Case Test`(): Unit = runTest {
        val expected = moviesModel
        // Given
        `when`(getTopRatedMoviesUseCase()).thenReturn(moviesModelFlow)
        moviesViewModel.moviesUiState.test {
            // When
            moviesViewModel.getTopRatedMovies()
            advanceUntilIdle()
            // Then
            assertEquals(MoviesUiState.Loading(isLoading = true), awaitItem())
            assertEquals(MoviesUiState.Movies(expected.take(MOVIES_COUNT)), awaitItem())
            assertEquals(MoviesUiState.Loading(isLoading = false), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `Get Movie By Id From Use Case Test`(): Unit = runTest {
        val expected = movieModel
        // Given
        `when`(getMovieByIdUseCase(movieId = 0)).thenReturn(movieModelFlow)
        moviesViewModel.moviesUiState.test {
            // When
            moviesViewModel.getMovieById(movieId = 0)
            advanceUntilIdle()
            // Then
            assertEquals(MoviesUiState.Loading(isLoading = true), awaitItem())
            assertEquals(MoviesUiState.Movie(expected), awaitItem())
            assertEquals(MoviesUiState.Loading(isLoading = false), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
