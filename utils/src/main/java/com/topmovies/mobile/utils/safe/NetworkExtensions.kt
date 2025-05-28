/*
 * NetworkExtensions.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.utils.safe

import retrofit2.Response

inline fun <R, T> Response<R>.toResource(action: R.() -> T): Resource<T> =
    if (isSuccessful) {
        val body = body()
        if (body != null) {
            Resource.Success(body.action())
        } else {
            Resource.Error(
                code = code(),
                message = message(),
            )
        }
    } else {
        Resource.Error(
            code = code(),
            message = errorBody()?.string() ?: message(),
        )
    }

fun <T> Response<T>.toResource(): Resource<T> =
    if (isSuccessful) {
        val body = body()
        if (body != null) {
            Resource.Success(body)
        } else {
            Resource.Error(
                code = code(),
                message = message(),
            )
        }
    } else {
        Resource.Error(
            code = code(),
            message = errorBody()?.string() ?: message(),
        )
    }
