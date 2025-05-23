/*
 * Logs.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.utils.logger

import android.util.Log
import com.topmovies.mobile.utils.extension.Constants.ERROR
import com.topmovies.mobile.utils.extension.Constants.NO_MESSAGE

fun log(message: String) {
    Log.d(ERROR, message)
}

fun log(
    className: Any,
    message: String?,
) {
    Log.d(className.javaClass.simpleName, message ?: NO_MESSAGE)
}
