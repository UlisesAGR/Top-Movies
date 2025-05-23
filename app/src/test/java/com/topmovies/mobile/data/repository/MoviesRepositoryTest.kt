/*
 * MoviesRepositoryTest.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.data.repository

import com.topmovies.mobile.data.local.source.MoviesLocalSource
import com.topmovies.mobile.data.netwotk.source.MoviesNetworkSource
import com.topmovies.mobile.utils.DispatcherRule
import com.topmovies.mobile.utils.MovieMock.movieDataResponse
import com.topmovies.mobile.utils.MovieMock.movieEntity
import com.topmovies.mobile.utils.MovieMock.movieModel
import com.topmovies.mobile.utils.MovieMock.movieModelMapper
import com.topmovies.mobile.utils.MovieMock.moviesDataResponse
import com.topmovies.mobile.utils.MovieMock.moviesEntity
import com.topmovies.mobile.utils.MovieMock.moviesModel
import com.topmovies.mobile.utils.MovieMock.moviesModelMapper
import com.topmovies.mobile.utils.extension.Resource
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
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
class MoviesRepositoryTest {

    private lateinit var moviesRepositoryImpl: MoviesRepositoryImpl

    @Mock
    lateinit var moviesNetworkSource: MoviesNetworkSource

    @Mock
    lateinit var moviesLocalSource: MoviesLocalSource

    @get:Rule
    val dispatcherRule = DispatcherRule()

    @Before
    fun setUp() {
        moviesRepositoryImpl = MoviesRepositoryImpl(
            moviesNetworkSource,
            moviesLocalSource,
            StandardTestDispatcher(),
        )
    }

    @Test
    fun `Get Top Rated Movies From Network Source Test`(): Unit = runTest {
        val expected = moviesModelMapper
        // Given
        `when`(moviesLocalSource.getMovies()).thenReturn(emptyList(), moviesEntity)
        `when`(moviesNetworkSource.getTopRatedMovies()).thenReturn(moviesDataResponse)
        // When
        val actual = moviesRepositoryImpl.getTopRatedMovies().first()
        // Then
        if (actual is Resource.Success) {
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `Get Top Rated Movies From Local Source Test`(): Unit = runTest {
        val expected = moviesModel
        // Given
        `when`(moviesLocalSource.getMovies()).thenReturn(moviesEntity)
        // When
        val actual = moviesRepositoryImpl.getTopRatedMovies().first()
        // Then
        if (actual is Resource.Success) {
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `Get Movie By Id From Network Source Test`(): Unit = runTest {
        val expected = movieModelMapper
        // Given
        `when`(moviesLocalSource.getMovieById(movieId = 0)).thenReturn(null, movieEntity)
        `when`(moviesNetworkSource.getMovieById(movieId = 0)).thenReturn(movieDataResponse)
        // When
        val actual = moviesRepositoryImpl.getMovieById(movieId = 0).first()
        // Then
        if (actual is Resource.Success) {
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `Get Movie By Id From Local Source Test`(): Unit = runTest {
        val expected = movieModel
        // Given
        `when`(moviesLocalSource.getMovieById(movieId = 0)).thenReturn(movieEntity)
        // When
        val actual = moviesRepositoryImpl.getMovieById(movieId = 0).first()
        // Then
        if (actual is Resource.Success) {
            assertEquals(expected, actual)
        }
    }
}
