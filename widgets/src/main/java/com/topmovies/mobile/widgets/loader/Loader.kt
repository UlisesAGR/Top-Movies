/*
 * Loader.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.widgets.loader

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.topmovies.mobile.utils.logger.log
import com.topmovies.mobile.utils.ui.show
import com.topmovies.mobile.widgets.R
import com.topmovies.mobile.widgets.databinding.LoaderBinding

class Loader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        LoaderBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.Loader).apply {
            runCatching {
                setText(getString(R.styleable.Loader_textLoader))
            }.onSuccess {
                recycle()
            }.onFailure { exception ->
                log(
                    className = this@Loader,
                    message = exception.message ?: "",
                )
            }
        }
    }

    private fun setText(text: String?) {
        with(binding.textLoading) {
            if (!text.isNullOrEmpty()) {
                this.text = text
                show()
            }
        }
    }
}
