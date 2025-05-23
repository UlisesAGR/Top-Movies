/*
 * ErrorMessageProvider.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.presentation.provider

interface ErrorMessageProvider {
    fun getUserMessage(cause: Throwable?): String
}
