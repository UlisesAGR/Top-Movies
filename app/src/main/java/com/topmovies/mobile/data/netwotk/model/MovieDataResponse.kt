/*
 * MovieDataResponse.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.data.netwotk.model

import com.google.gson.annotations.SerializedName

data class MovieDataResponse(
    @SerializedName("results") val results: List<MovieResponse>,
)
