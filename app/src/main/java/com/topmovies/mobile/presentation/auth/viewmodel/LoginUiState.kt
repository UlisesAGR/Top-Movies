/*
 * LoginUiState.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.presentation.auth.viewmodel

import com.topmovies.mobile.domain.model.auth.LoginStatus

sealed class LoginUiState {
    internal data class Loading(val isLoading: Boolean) : LoginUiState()
    internal data class StatusLoginForm(val status: LoginStatus) : LoginUiState()
    internal data object GoToMovies : LoginUiState()
}
