/*
 * Intent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.util

import android.app.Dialog
import android.content.Intent
import android.view.KeyEvent
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.nextActivityFinish(destination: FragmentActivity) {
    Intent(this, destination::class.java).apply {
        startActivity(this)
        finish()
    }
}

fun FragmentActivity.nextActivity(destination: FragmentActivity) {
    Intent(this, destination::class.java).apply {
        startActivity(this)
    }
}

fun Dialog?.onBackPressed(onClick: () -> Unit) {
    this?.setOnKeyListener { _, keyCode, event ->
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
            onClick()
            return@setOnKeyListener true
        }
        false
    }
}
