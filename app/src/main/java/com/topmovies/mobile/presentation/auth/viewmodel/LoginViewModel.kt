/*
 * LoginViewModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.presentation.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.topmovies.mobile.domain.model.auth.LoginStatus
import com.topmovies.mobile.domain.usecase.auth.ValidationLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validationLoginUseCase: ValidationLoginUseCase,
) : ViewModel() {

    private var _loginUiState = MutableSharedFlow<LoginUiState>()
    val loginUiState: SharedFlow<LoginUiState> = _loginUiState

    fun validateLogin(
        email: String,
        password: String,
    ) = viewModelScope.launch {
        when (validationLoginUseCase(email, password)) {
            LoginStatus.EMPTY_EMAIL ->
                _loginUiState.emit(LoginUiState.StatusLoginForm(status = LoginStatus.EMPTY_EMAIL))

            LoginStatus.INVALID_EMAIL ->
                _loginUiState.emit(LoginUiState.StatusLoginForm(status = LoginStatus.INVALID_EMAIL))

            LoginStatus.EMPTY_PASSWORD ->
                _loginUiState.emit(LoginUiState.StatusLoginForm(status = LoginStatus.EMPTY_PASSWORD))

            LoginStatus.SUCCESS -> login()
        }
    }

    private fun login() = viewModelScope.launch {
        _loginUiState.emit(LoginUiState.Loading(isLoading = true))
        delay(2000)
        _loginUiState.apply {
            emit(LoginUiState.GoToMovies)
            emit(LoginUiState.Loading(isLoading = false))
        }
    }
}
