/*
 * DetailDialogConfig.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.presentation.movies.ui.view.dialog

import androidx.fragment.app.FragmentManager
import com.topmovies.mobile.domain.model.movies.MovieModel
import com.topmovies.mobile.presentation.movies.ui.view.dialog.DetailDialogFragment.Companion.DETAIL_DIALOG_FRAGMENT_TAG

class DetailDialogConfig(val movie: MovieModel?) {

    private val detailDialogFragment: DetailDialogFragment by lazy {
        DetailDialogFragment.newInstance(this)
    }

    fun showDialog(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .add(detailDialogFragment, DETAIL_DIALOG_FRAGMENT_TAG)
            .commitAllowingStateLoss()
    }

    fun setCancelable(isCancelable: Boolean) {
        detailDialogFragment.isCancelable = isCancelable
    }
}
