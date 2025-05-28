/*
 * LocalExtensions.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.utils.safe

inline fun <T> toResource(action: () -> T): Resource<T> {
    return try {
        val result = action()
        Resource.Success(result)
    } catch (exception: Exception) {
        Resource.Error(
            code = 0,
            message = exception.message,
        )
    }
}
