/*
 * MovieResponse.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.data.netwotk.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("vote_average") val voteAverage: Float,
    @SerializedName("poster_path") val posterPath: String?,
)
