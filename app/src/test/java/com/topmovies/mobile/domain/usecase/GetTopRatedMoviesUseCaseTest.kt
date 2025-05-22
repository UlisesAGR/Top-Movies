/*
 * GetTopRatedMoviesUseCaseTest.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.domain.usecase

import com.topmovies.mobile.domain.repository.MoviesRepository
import com.topmovies.mobile.domain.usecase.movies.GetTopRatedMoviesUseCase
import com.topmovies.mobile.utils.DispatcherRule
import com.topmovies.mobile.utils.MovieMock.moviesModel
import com.topmovies.mobile.utils.MovieMock.moviesModelFlow
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
class GetTopRatedMoviesUseCaseTest {

    private lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase

    @Mock
    lateinit var moviesRepository: MoviesRepository

    @get:Rule
    val dispatcherRule = DispatcherRule()

    @Before
    fun setUp() {
        getTopRatedMoviesUseCase = GetTopRatedMoviesUseCase(
            moviesRepository,
        )
    }

    @Test
    fun `Get Top Rated Movies Use Case From Repository Test`(): Unit = runTest {
        val expected = moviesModel
        // Given
        `when`(moviesRepository.getTopRatedMovies()).thenReturn(moviesModelFlow)
        // When
        val actual = getTopRatedMoviesUseCase()
        // Then
        assertEquals(expected, actual.first())
    }
}
