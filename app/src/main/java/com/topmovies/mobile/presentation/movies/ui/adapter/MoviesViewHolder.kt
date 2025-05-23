/*
 * MoviesViewHolder.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.presentation.movies.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.topmovies.mobile.R
import com.topmovies.mobile.databinding.ItemMovieBinding
import com.topmovies.mobile.domain.model.movies.MovieModel
import com.topmovies.mobile.utils.extension.toOneDecimalString
import com.topmovies.mobile.utils.ui.load
import com.topmovies.mobile.utils.ui.setOnSafeClickListener

class MoviesViewHolder(
    private val binding: ItemMovieBinding,
) : RecyclerView.ViewHolder(binding.root) {

    private val context = binding.root.context

    fun render(
        movie: MovieModel,
        onMovieSelected: (MovieModel) -> Unit,
    ) = with(binding) {
        movie.apply {
            setMovieImage(posterPath)
            textCircleRating.setText(voteAverage.toOneDecimalString())
            titleTextView.text = title ?: context.getString(R.string.n_a)
            itemView.setOnSafeClickListener { onMovieSelected(this) }
        }
    }

    private fun setMovieImage(posterPath: String?) {
        binding.movieImageView.load(
            posterPath,
            loadImage = android.R.drawable.progress_horizontal,
            errorImage = android.R.drawable.progress_horizontal,
        )
    }
}
