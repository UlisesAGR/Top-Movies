/*
 * MovieMapperTest.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.domain.mapper

import com.topmovies.mobile.utils.MovieMock.movieEntity
import com.topmovies.mobile.utils.MovieMock.movieEntityMapper
import com.topmovies.mobile.utils.MovieMock.movieModel
import com.topmovies.mobile.utils.MovieMock.movieModelMapper
import com.topmovies.mobile.utils.MovieMock.movieResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MovieMapperTest {

    @Test
    fun `Validate Transformation Movie Response To Entity`() = runTest {
        // Given
        val expected = movieEntityMapper
        // When
        val actual = movieResponse.toEntity()
        // Then
        assertEquals(expected, actual)
    }

    @Test
    fun `Validate Transformation Movie Entity To Domain`() = runTest {
        // Given
        val expected = movieModel
        // When
        val actual = movieEntity.toDomain()
        // Then
        assertEquals(expected, actual)
    }

    @Test
    fun `Validate Transformation Movie Response To Domain`() = runTest {
        // Given
        val expected = movieModelMapper
        // When
        val actual = movieResponse.toDomain()
        // Then
        assertEquals(expected, actual)
    }
}
