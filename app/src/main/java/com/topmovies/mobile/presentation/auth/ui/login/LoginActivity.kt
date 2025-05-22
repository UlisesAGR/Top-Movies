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
import com.topmovies.mobile.presentation.auth.ui.welcome.WelcomeActivity
import com.topmovies.mobile.presentation.auth.viewmodel.LoginUiState
import com.topmovies.mobile.presentation.auth.viewmodel.LoginViewModel
import com.topmovies.mobile.presentation.movies.ui.activity.MoviesActivity
import com.topmovies.mobile.utils.extension.collect
import com.topmovies.mobile.utils.extension.nextActivityFinish
import com.topmovies.mobile.utils.extension.onBackPressed
import com.topmovies.mobile.utils.ui.gone
import com.topmovies.mobile.utils.ui.hideSoftKeyboard
import com.topmovies.mobile.utils.ui.onTextChanged
import com.topmovies.mobile.utils.ui.show
import com.topmovies.mobile.utils.ui.viewBinding
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
        backImageButton.setOnClickListener {
            goToWelcome()
        }
        onBackPressed {
            goToWelcome()
        }
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
                    setStatusLoading(state.isLoading)
                }
                is LoginUiState.StatusLoginForm -> {
                    validateStatusLoginForm(state.status)
                }
                is LoginUiState.GoToMovies -> {
                    clearEditText()
                    goToMovies()
                }
            }
        }
    }

    private fun setStatusLoading(isLoading: Boolean) = with(binding) {
        if (isLoading) loginProgressBar.show()
        else loginProgressBar.gone()
    }

    private fun validateStatusLoginForm(status: LoginStatus) = with(binding) {
        when (status) {
            LoginStatus.EMPTY_EMAIL -> emailBox.error = getString(R.string.enter_your_email)
            LoginStatus.INVALID_EMAIL -> emailBox.error = getString(R.string.email_invalid)
            LoginStatus.EMPTY_PASSWORD -> passwordBox.error = getString(R.string.enter_your_password)
            else -> clearFocus()
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

    private fun clearFocus() = with(binding) {
        emailEditText.clearFocus()
        passwordEditText.clearFocus()
    }

    private fun clearEditText() = with(binding) {
        emailEditText.setText("")
        passwordEditText.setText("")
    }

    private fun goToMovies() {
        nextActivityFinish(
            destination = MoviesActivity(),
            animIn = R.anim.fade_in,
            animOut = R.anim.fade_out,
        )
    }

    private fun goToWelcome() {
        nextActivityFinish(
            destination = WelcomeActivity(),
            animIn = R.anim.slide_in_right,
            animOut = R.anim.slide_out_right,
        )
    }
}
