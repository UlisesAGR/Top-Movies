/*
 * MovieMapper.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.domain.mapper

import com.topmovies.mobile.BuildConfig.IMAGE_BASE_URL
import com.topmovies.mobile.data.local.model.MovieEntity
import com.topmovies.mobile.data.netwotk.model.MovieResponse
import com.topmovies.mobile.domain.model.MovieModel

fun MovieResponse.toEntity(): MovieEntity =
    MovieEntity(
        id,
        title,
        releaseDate,
        overview,
        voteAverage,
        posterPath = IMAGE_BASE_URL + posterPath,
    )

fun MovieEntity.toDomain(): MovieModel =
    MovieModel(id, title, releaseDate, overview, voteAverage, posterPath)

fun MovieResponse.toDomain(): MovieModel =
    MovieModel(
        id,
        title,
        releaseDate,
        overview,
        voteAverage,
        posterPath = IMAGE_BASE_URL + posterPath,
    )
