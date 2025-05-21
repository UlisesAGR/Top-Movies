/*
 * LoadImage.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.utils.ui

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.load(
    imageUrl: String?,
    loadImage: Int,
    errorImage: Int,
) {
    Glide.with(this)
        .load(imageUrl)
        .transition(DrawableTransitionOptions.withCrossFade())
        .placeholder(loadImage)
        .error(errorImage)
        .into(this)
}
