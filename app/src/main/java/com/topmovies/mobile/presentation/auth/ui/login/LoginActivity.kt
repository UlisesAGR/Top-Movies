/*
 * LoginActivity.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.presentation.auth.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.topmovies.mobile.R
import com.topmovies.mobile.databinding.ActivityLoginBinding
import com.topmovies.mobile.domain.model.auth.LoginStatus
import com.topmovies.mobile.presentation.auth.viewmodel.LoginUiState
import com.topmovies.mobile.presentation.auth.viewmodel.LoginViewModel
import com.topmovies.mobile.presentation.movies.ui.view.activity.MoviesActivity
import com.topmovies.mobile.util.collect
import com.topmovies.mobile.util.gone
import com.topmovies.mobile.util.hideSoftKeyboard
import com.topmovies.mobile.util.nextActivityFinish
import com.topmovies.mobile.util.onTextChanged
import com.topmovies.mobile.util.show
import com.topmovies.mobile.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setInitUi()
    }

    private fun setInitUi() {
        setListeners()
        setFlows()
    }

    private fun setListeners() = with(binding) {
        sigInButton.setOnClickListener {
            it.hideSoftKeyboard()
            loginViewModel.validateLogin(
                email = emailEditText.text.toString(),
                password = passwordEditText.text.toString(),
            )
        }
        clearErrors()
    }

    private fun setFlows() {
        collect(loginViewModel.loginUiState) { state ->
            when (state) {
                is LoginUiState.Loading -> {
                    statusLoading(state.isLoading)
                }

                is LoginUiState.StatusLoginForm -> {
                    validateStatusLoginForm(state.status)
                }

                is LoginUiState.GoToMovies -> {
                    nextActivityFinish(MoviesActivity())
                }
            }
        }
    }

    private fun statusLoading(isLoading: Boolean) = with(binding) {
        if (isLoading) loginProgressBar.show()
        else loginProgressBar.gone()
    }

    private fun validateStatusLoginForm(status: LoginStatus) = with(binding) {
        when (status) {
            LoginStatus.EMPTY_EMAIL -> emailBox.error = getString(R.string.enter_your_email)
            LoginStatus.INVALID_EMAIL -> emailBox.error = getString(R.string.email_invalid)
            LoginStatus.EMPTY_PASSWORD -> passwordBox.error = getString(R.string.enter_your_password)
            else -> {}
        }
    }

    private fun clearErrors() = with(binding) {
        emailEditText.onTextChanged {
            emailBox.error = null
        }
        passwordEditText.onTextChanged {
            passwordBox.error = null
        }
    }
}
