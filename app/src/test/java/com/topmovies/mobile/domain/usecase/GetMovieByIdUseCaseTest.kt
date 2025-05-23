/*
 * GetMovieByIdUseCaseTest.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.domain.usecase

import com.topmovies.mobile.domain.repository.MoviesRepository
import com.topmovies.mobile.domain.usecase.movies.GetMovieByIdUseCase
import com.topmovies.mobile.utils.DispatcherRule
import com.topmovies.mobile.utils.MovieMock.movieModel
import com.topmovies.mobile.utils.MovieMock.movieModelFlow
import com.topmovies.mobile.utils.extension.Resource
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@HiltAndroidTest
class GetMovieByIdUseCaseTest {

    private lateinit var getMovieByIdUseCase: GetMovieByIdUseCase

    @Mock
    lateinit var moviesRepository: MoviesRepository

    @get:Rule
    val dispatcherRule = DispatcherRule()

    @Before
    fun setUp() {
        getMovieByIdUseCase = GetMovieByIdUseCase(
            moviesRepository,
        )
    }

    @Test
    fun `Get Movie By Id Use Case From Repository Test`(): Unit = runTest {
        val expected = movieModel
        // Given
        `when`(moviesRepository.getMovieById(movieId = 0)).thenReturn(movieModelFlow)
        // When
        val actual = getMovieByIdUseCase(movieId = 0).first()
        // Then
        if (actual is Resource.Success) {
            assertEquals(expected, actual.data)
        }
    }
}
