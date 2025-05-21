/*
 * DetailDialogFragment.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.presentation.ui.view.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.topmovies.mobile.R
import com.topmovies.mobile.databinding.FragmentDetailBinding
import com.topmovies.mobile.domain.model.MovieModel
import com.topmovies.mobile.util.load
import com.topmovies.mobile.util.onBackPressed
import com.topmovies.mobile.util.setAnimationEnd
import com.topmovies.mobile.util.setAnimationStart
import com.topmovies.mobile.util.show
import com.topmovies.mobile.util.toOneDecimalString
import com.topmovies.mobile.util.viewBinding

class DetailDialogFragment : DialogFragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)

    private lateinit var detailDialogConfig: DetailDialogConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitUi()
    }

    private fun setInitUi() {
        setAnimation()
        setListeners()
        setData(data = detailDialogConfig.movie)
    }

    private fun setAnimation() {
        binding.root.setAnimationStart(animationId = R.anim.slide_up)
    }

    private fun setListeners() = with(binding) {
        toolbar.setNavigationOnClickListener {
            dismiss()
        }
        backButton.setOnClickListener {
            dismiss()
        }
        dialog.onBackPressed {
            dismiss()
        }
    }

    private fun setData(data: MovieModel?) {
        data?.let { movie ->
            setPosterPath(posterPath = movie.posterPath)
            setTitle(title = movie.title ?: getString(R.string.n_a))
            setCircleRating(rating = movie.voteAverage.toOneDecimalString())
            setReleaseDate(releaseDate = movie.releaseDate ?: getString(R.string.n_a))
            setOverview(overview = movie.overview ?: getString(R.string.n_a))
        } ?: run {
            dismiss()
        }
    }

    private fun setPosterPath(posterPath: String?) =
        with(binding.recipeImageView) {
            if (!posterPath.isNullOrEmpty()) {
                load(
                    posterPath,
                    loadImage = android.R.drawable.progress_horizontal,
                    errorImage = android.R.drawable.progress_horizontal,
                )
                show()
            }
        }

    private fun setCircleRating(rating: String?) =
        with(binding.textCircleRating) {
            if (!rating.isNullOrEmpty()) {
                setText(rating)
                show()
            }
        }

    private fun setTitle(title: String?) =
        with(binding.nameTextView) {
            if (!title.isNullOrEmpty()) {
                text = title
                show()
            }
        }

    private fun setReleaseDate(releaseDate: String?) =
        with(binding.releaseDateTextView) {
            if (!releaseDate.isNullOrEmpty()) {
                text = releaseDate
                show()
            }
        }

    private fun setOverview(overview: String?) =
        with(binding.overviewTextView) {
            if (!overview.isNullOrEmpty()) {
                text = overview
                show()
            }
        }

    override fun dismiss() {
        binding.root.setAnimationEnd(
            animationId = R.anim.slide_down,
            onAnimationEnd = {
                super.dismiss()
            },
        )
    }

    companion object {
        const val DETAIL_DIALOG_FRAGMENT_TAG = "DetailDialogFragment"

        fun newInstance(
            detailDialogConfig: DetailDialogConfig,
        ): DetailDialogFragment = DetailDialogFragment().apply {
            this.detailDialogConfig = detailDialogConfig
        }
    }
}
