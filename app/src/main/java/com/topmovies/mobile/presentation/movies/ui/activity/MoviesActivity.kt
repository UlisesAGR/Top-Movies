/*
 * MoviesActivity.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.presentation.movies.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.topmovies.mobile.R
import com.topmovies.mobile.databinding.ActivityMoviesBinding
import com.topmovies.mobile.domain.model.movies.MovieModel
import com.topmovies.mobile.presentation.movies.ui.adapter.MoviesAdapter
import com.topmovies.mobile.presentation.movies.ui.dialog.DetailDialogConfig
import com.topmovies.mobile.presentation.movies.viewmodel.MoviesUiState
import com.topmovies.mobile.presentation.movies.viewmodel.MoviesViewModel
import com.topmovies.mobile.util.toUserMessage
import com.topmovies.mobile.utils.extension.collect
import com.topmovies.mobile.utils.ui.gone
import com.topmovies.mobile.utils.ui.show
import com.topmovies.mobile.utils.ui.toast
import com.topmovies.mobile.utils.ui.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMoviesBinding::inflate)

    private val moviesViewModel: MoviesViewModel by viewModels()

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setInitUi()
    }

    private fun setInitUi() {
        moviesViewModel.getTopRatedMovies()
        setListeners()
        setMoviesAdapter()
        setMoviesRecyclerView()
        setFlows()
    }

    private fun setListeners() = with(binding) {
        swipeRefreshLayout.setOnRefreshListener {
            moviesViewModel.getTopRatedMovies()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setMoviesAdapter() {
        moviesAdapter = MoviesAdapter(
            onMovieSelected = { product ->
                moviesViewModel.getMovieById(movieId = product.id)
            },
        )
    }

    private fun setMoviesRecyclerView() {
        binding.moviesRecyclerView.apply {
            setHasFixedSize(true)
            adapter = moviesAdapter
        }
    }

    private fun setFlows() {
        collect(moviesViewModel.moviesUiState) { state ->
            when (state) {
                is MoviesUiState.Loading -> {
                    statusLoading(state.isLoading)
                }
                is MoviesUiState.Error -> {
                    binding.moviesEmptyState.show()
                    toast(toUserMessage(state.cause))
                }
                is MoviesUiState.Movies -> {
                    moviesAdapter.setMovies(
                        movies = state.movies,
                        onChanged = { itemCount ->
                            validateEmptyState(itemCount)
                        }
                    )
                }
                is MoviesUiState.Movie -> {
                    showDetailDialog(state.movie)
                }
            }
        }
    }

    private fun statusLoading(isLoading: Boolean) = with(binding) {
        if (isLoading) moviesProgressBar.show()
        else moviesProgressBar.gone()
    }

    private fun validateEmptyState(itemCount: Int) = with(binding) {
        if (itemCount == 0) {
            moviesEmptyState.show()
            moviesRecyclerView.gone()
        } else {
            moviesEmptyState.gone()
            moviesRecyclerView.show()
        }
    }

    private fun showDetailDialog(movie: MovieModel?) {
        movie?.let {
            DetailDialogConfig(movie).also { config ->
                config.apply {
                    showDialog(supportFragmentManager)
                    setCancelable(true)
                }
            }
        } ?: run {
            toast(getString(R.string.please_try_again_later))
        }
    }
}
