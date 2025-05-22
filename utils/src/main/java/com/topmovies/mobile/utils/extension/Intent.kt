/*
 * Intent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
@file:Suppress("DEPRECATION")
package com.topmovies.mobile.utils.extension

import android.app.Activity.OVERRIDE_TRANSITION_OPEN
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.view.KeyEvent
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.nextActivityFinish(destination: FragmentActivity) {
    val intent = Intent(this, destination::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent)
    finish()
}


fun FragmentActivity.nextActivity(destination: FragmentActivity) {
    Intent(this, destination::class.java).apply {
        startActivity(this)
    }
}

fun FragmentActivity.nextActivityFinish(
    destination: FragmentActivity,
    animIn: Int = 0,
    animOut: Int = 0,
) {
    Intent(this, destination::class.java).apply {
        startActivity(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            overrideActivityTransition(OVERRIDE_TRANSITION_OPEN, animIn, animOut)
        } else {
            overridePendingTransition(animIn, animOut)
        }
        finish()
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
