/*
 * MoviesNetworkSourceTest.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.data.network

import com.topmovies.mobile.data.netwotk.service.MoviesService
import com.topmovies.mobile.data.netwotk.source.MoviesNetworkSourceImpl
import com.topmovies.mobile.utils.DispatcherRule
import com.topmovies.mobile.utils.MovieMock.movieDataResponse
import com.topmovies.mobile.utils.MovieMock.movieResponse
import com.topmovies.mobile.utils.MovieMock.moviesDataResponse
import com.topmovies.mobile.utils.MovieMock.moviesResponse
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
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
class MoviesNetworkSourceTest {

    private lateinit var moviesNetworkSourceImpl: MoviesNetworkSourceImpl

    @Mock
    lateinit var moviesService: MoviesService

    @get:Rule
    val dispatcherRule = DispatcherRule()

    @Before
    fun setUp() {
        moviesNetworkSourceImpl = MoviesNetworkSourceImpl(
            moviesService,
        )
    }

    @Test
    fun `Get Top Rated Movies from Network Test`(): Unit = runTest {
        val expected = moviesResponse
        // Given
        `when`(moviesService.getTopRatedMovies()).thenReturn(moviesDataResponse)
        // When
        val actual = moviesNetworkSourceImpl.getTopRatedMovies()
        // Then
        assertEquals(expected, actual.body()?.results)
    }

    @Test
    fun `Get Movie By Id from Network Test`(): Unit = runTest {
        val expected = movieResponse
        // Given
        `when`(moviesService.getMovieById(movieId = 0)).thenReturn(movieDataResponse)
        // When
        val actual = moviesNetworkSourceImpl.getMovieById(movieId = 0)
        // Then
        assertEquals(expected, actual.body())
    }
}
