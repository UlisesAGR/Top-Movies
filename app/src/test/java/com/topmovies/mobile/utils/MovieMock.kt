/*
 * MovieMock.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.utils

import com.topmovies.mobile.data.local.model.MovieEntity
import com.topmovies.mobile.data.netwotk.model.MovieDataResponse
import com.topmovies.mobile.data.netwotk.model.MovieResponse
import com.topmovies.mobile.domain.model.movies.MovieModel
import kotlinx.coroutines.flow.flowOf
import retrofit2.Response

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

    val movieResponse = MovieResponse(
        id = 0,
        title = "Movie",
        releaseDate = "10-03-1200",
        overview = "Description",
        voteAverage = 0f,
        posterPath = "www.url.com",
    )

    val moviesResponse = listOf(
        MovieResponse(
            id = 0,
            title = "Movie",
            releaseDate = "10-03-1200",
            overview = "Description",
            voteAverage = 0f,
            posterPath = "www.url.com",
        ),
        MovieResponse(
            id = 2,
            title = "Movie",
            releaseDate = "10-03-1200",
            overview = "Description",
            voteAverage = 0f,
            posterPath = "www.url.com",
        ),
    )

    val movieModel = MovieModel(
        id = 0,
        title = "Movie",
        releaseDate = "10-03-1200",
        overview = "Description",
        voteAverage = 0f,
        posterPath = "www.url.com",
    )

    val moviesModel = listOf(
        MovieModel(
            id = 0,
            title = "Movie",
            releaseDate = "10-03-1200",
            overview = "Description",
            voteAverage = 0f,
            posterPath = "www.url.com",
        ),
        MovieModel(
            id = 2,
            title = "Movie",
            releaseDate = "10-03-1200",
            overview = "Description",
            voteAverage = 0f,
            posterPath = "www.url.com",
        ),
    )

    val moviesDataResponse: Response<MovieDataResponse> =
        Response.success(MovieDataResponse(results = moviesResponse))

    val movieDataResponse: Response<MovieResponse?> =
        Response.success(movieResponse)

    val moviesModelFlow = flowOf(moviesModel)

    val movieModelFlow = flowOf(movieModel)
}
