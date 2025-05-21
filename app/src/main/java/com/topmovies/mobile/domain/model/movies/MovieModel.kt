/*
 * MovieModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.domain.model.movies

data class MovieModel(
    val id: Int,
    val title: String?,
    val releaseDate: String?,
    val overview: String?,
    val voteAverage: Float,
    val posterPath: String?,
)
