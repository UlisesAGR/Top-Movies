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
import com.topmovies.mobile.utils.extension.Resource
import kotlinx.coroutines.flow.flowOf
import retrofit2.Response

object MovieMock {

    val movieEntityMapper = MovieEntity(
        id = 0,
        title = "Movie",
        releaseDate = "10-03-1200",
        overview = "Description",
        voteAverage = 0f,
        posterPath = "https://image.tmdb.org/t/p/w500image.jpg",
    )

    val movieEntity = MovieEntity(
        id = 0,
        title = "Movie",
        releaseDate = "10-03-1200",
        overview = "Description",
        voteAverage = 0f,
        posterPath = "image.jpg",
    )

    val moviesEntity = listOf(
        MovieEntity(
            id = 0,
            title = "Movie",
            releaseDate = "10-03-1200",
            overview = "Description",
            voteAverage = 0f,
            posterPath = "image.jpg",
        ),
        MovieEntity(
            id = 2,
            title = "Movie",
            releaseDate = "10-03-1200",
            overview = "Description",
            voteAverage = 0f,
            posterPath = "image.jpg",
        ),
    )

    val movieResponse = MovieResponse(
        id = 0,
        title = "Movie",
        releaseDate = "10-03-1200",
        overview = "Description",
        voteAverage = 0f,
        posterPath = "image.jpg",
    )

    val moviesResponse = listOf(
        MovieResponse(
            id = 0,
            title = "Movie",
            releaseDate = "10-03-1200",
            overview = "Description",
            voteAverage = 0f,
            posterPath = "image.jpg",
        ),
        MovieResponse(
            id = 2,
            title = "Movie",
            releaseDate = "10-03-1200",
            overview = "Description",
            voteAverage = 0f,
            posterPath = "image.jpg",
        ),
    )

    val movieModelMapper = MovieModel(
        id = 0,
        title = "Movie",
        releaseDate = "10-03-1200",
        overview = "Description",
        voteAverage = 0f,
        posterPath = "https://image.tmdb.org/t/p/w500image.jpg",
    )

    val movieModel = MovieModel(
        id = 0,
        title = "Movie",
        releaseDate = "10-03-1200",
        overview = "Description",
        voteAverage = 0f,
        posterPath = "image.jpg",
    )

    val moviesModelMapper = listOf(
        MovieModel(
            id = 0,
            title = "Movie",
            releaseDate = "10-03-1200",
            overview = "Description",
            voteAverage = 0f,
            posterPath = "https://image.tmdb.org/t/p/w500image.jpg",
        ),
        MovieModel(
            id = 2,
            title = "Movie",
            releaseDate = "10-03-1200",
            overview = "Description",
            voteAverage = 0f,
            posterPath = "https://image.tmdb.org/t/p/w500image.jpg",
        ),
    )

    val moviesModel = listOf(
        MovieModel(
            id = 0,
            title = "Movie",
            releaseDate = "10-03-1200",
            overview = "Description",
            voteAverage = 0f,
            posterPath = "image.jpg",
        ),
        MovieModel(
            id = 2,
            title = "Movie",
            releaseDate = "10-03-1200",
            overview = "Description",
            voteAverage = 0f,
            posterPath = "image.jpg",
        ),
    )

    val moviesDataResponse: Response<MovieDataResponse> =
        Response.success(MovieDataResponse(results = moviesResponse))

    val movieDataResponse: Response<MovieResponse?> =
        Response.success(movieResponse)

    val moviesModelFlow = flowOf(Resource.Success(moviesModel))

    val movieModelFlow = flowOf(Resource.Success(movieModel))
}
