/*
 * Regex.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.utils.extension

private val emailRegex = Regex(pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")

fun String.isValidEmail(): Boolean =
    emailRegex.matches(this)
