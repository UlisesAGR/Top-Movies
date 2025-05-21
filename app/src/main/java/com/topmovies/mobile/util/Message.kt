/*
 * Message.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.util

import android.content.Context
import android.widget.Toast

fun Context.toast(message: String?) =
    message?.takeIf { it.isNotBlank() }?.let {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }
