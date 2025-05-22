/*
 * MoviesLocalSourceTest.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.topmovies.mobile.data.local.dao.MovieDao
import com.topmovies.mobile.data.local.database.Database
import com.topmovies.mobile.data.local.source.MoviesLocalSourceImpl
import com.topmovies.mobile.utils.DispatcherRule
import com.topmovies.mobile.utils.MovieMock.movieEntity
import com.topmovies.mobile.utils.MovieMock.moviesEntity
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
@HiltAndroidTest
class MoviesLocalSourceTest {

    private lateinit var moviesLocalSourceImpl: MoviesLocalSourceImpl

    @Inject
    lateinit var database: Database

    @Inject
    lateinit var movieDao: MovieDao

    @get:Rule
    val dispatcherRule = DispatcherRule()

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            Database::class.java,
        ).allowMainThreadQueries().build()
        movieDao = database.movieDao()
        moviesLocalSourceImpl = MoviesLocalSourceImpl(
            movieDao,
        )
    }

    @Test
    fun `Insert All Movies In Database And Get Test`(): Unit = runTest {
        val expected = movieEntity
        // Given
        moviesLocalSourceImpl.insertAll(moviesEntity)
        // When
        val actual = moviesLocalSourceImpl.getMovies()
        // Then
        assertEquals(expected, actual.first())
    }

    @Test
    fun `Insert All Movies In Database And Get By Id Test`(): Unit = runTest {
        val expected = movieEntity
        // Given
        moviesLocalSourceImpl.insertAll(moviesEntity)
        // When
        val actual = moviesLocalSourceImpl.getMovieById(movieId = 0)
        // Then
        assertEquals(expected, actual)
    }

    @After
    fun closeDb() {
        database.close()
    }
}
