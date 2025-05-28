/*
 * Resource.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.utils.safe

sealed class Resource<out T>(
    val code: Int,
    val data: T? = null,
    val message: String? = null,
) {
    class Success<T>(data: T) : Resource<T>(
        code = SUCCESS,
        data = data,
    )

    class Error<T>(
        code: Int,
        message: String? = null,
    ) : Resource<T>(
        code = code,
        message = message,
    )

    companion object {
        const val SUCCESS = 200
    }
}
