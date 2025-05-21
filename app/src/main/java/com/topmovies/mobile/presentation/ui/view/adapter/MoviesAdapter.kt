/*
 * MoviesAdapter.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.presentation.ui.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.topmovies.mobile.databinding.ItemMovieBinding
import com.topmovies.mobile.domain.model.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesAdapter(
    var movies: List<MovieModel> = emptyList(),
    private val onMovieSelected: (MovieModel) -> Unit,
) : RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MoviesViewHolder = MoviesViewHolder(
        ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(
        holder: MoviesViewHolder,
        position: Int,
    ) {
        holder.render(movies[position], onMovieSelected)
    }

    override fun getItemCount(): Int = movies.size

    @SuppressLint("NotifyDataSetChanged")
    suspend inline fun setMovies(
        movies: List<MovieModel>,
        crossinline onChanged: (Int) -> Unit,
    ) = withContext(Dispatchers.Main) {
        this@MoviesAdapter.movies = movies
        notifyDataSetChanged()
        onChanged(movies.size)
    }
}
