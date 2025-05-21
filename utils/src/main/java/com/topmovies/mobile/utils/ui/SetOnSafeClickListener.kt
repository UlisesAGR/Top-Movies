/*
 * SetOnSafeClickListener.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.utils.ui

import android.view.View
import com.topmovies.mobile.utils.ui.SafeClick.Companion.CLICK_THRESHOLD_MS
import java.util.concurrent.atomic.AtomicLong

class SafeClick {

    companion object {

        const val CLICK_THRESHOLD_MS = 700L
        private val lastEmittedClickTimestamp = AtomicLong(0L)

        fun execute(
            clickThreshold: Long = CLICK_THRESHOLD_MS,
            lambda: () -> Unit,
        ) {
            val currentTime = System.currentTimeMillis()
            val lastClickTime = lastEmittedClickTimestamp.get()

            if (currentTime > lastClickTime + clickThreshold || currentTime < lastClickTime) {
                lastEmittedClickTimestamp.set(currentTime)
                lambda()
            }
        }
    }
}

fun View.setOnSafeClickListener(
    clickThreshold: Long = CLICK_THRESHOLD_MS,
    listener: (View) -> Unit,
) {
    setOnClickListener(
        object : OnSafeClickListener(clickThreshold) {
            override fun onSafeClick(view: View) {
                listener(view)
            }
        },
    )
}

fun View.setOnSafeClickListener(listener: View.OnClickListener) {
    setOnSafeClickListener(CLICK_THRESHOLD_MS) { listener.onClick(it) }
}

abstract class OnSafeClickListener(private val clickThreshold: Long) : View.OnClickListener {

    abstract fun onSafeClick(view: View)

    override fun onClick(view: View) {
        SafeClick.execute(clickThreshold) {
            onSafeClick(view)
        }
    }
}
