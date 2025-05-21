/*
 * MovieMock.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.utils

import com.topmovies.mobile.data.local.model.MovieEntity

object MovieMock {

    val movieEntity = MovieEntity(
        id = 0,
        title = "Movie",
        releaseDate = "10-03-1200",
        overview = "Description",
        voteAverage = 0f,
        posterPath = "www.url.com",
    )

    val moviesEntity = listOf(
        MovieEntity(
            id = 0,
            title = "Movie",
            releaseDate = "10-03-1200",
            overview = "Description",
            voteAverage = 0f,
            posterPath = "www.url.com",
        ),
        MovieEntity(
            id = 2,
            title = "Movie",
            releaseDate = "10-03-1200",
            overview = "Description",
            voteAverage = 0f,
            posterPath = "www.url.com",
        ),
    )
}
