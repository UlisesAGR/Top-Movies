/*
 * SoftKeyboard.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.util

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat

fun View.hideSoftKeyboard() {
    if (isAttachedToWindow) {
        val imm = ContextCompat.getSystemService(context, InputMethodManager::class.java)
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }
}

