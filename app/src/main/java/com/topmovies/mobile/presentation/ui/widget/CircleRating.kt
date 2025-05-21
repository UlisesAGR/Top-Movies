/*
 * CircleRating.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.presentation.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.topmovies.mobile.R
import com.topmovies.mobile.databinding.CircleRatingBinding
import com.topmovies.mobile.util.log
import com.topmovies.mobile.util.show

class CircleRating @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        CircleRatingBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.CircleRating).apply {
            runCatching {
                setText(getString(R.styleable.Loader_textLoader))
            }.onSuccess {
                recycle()
            }.onFailure { exception ->
                log(
                    className = this@CircleRating,
                    message = exception.message ?: "",
                )
            }
        }
    }

    fun setText(text: String?) {
        with(binding.circleRatingTextView) {
            if (!text.isNullOrEmpty()) {
                this.text = text
                show()
            }
        }
    }
}
