/*
 * WelcomeActivity.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.presentation.auth.ui.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.topmovies.mobile.R
import com.topmovies.mobile.databinding.ActivityWelcomeBinding
import com.topmovies.mobile.presentation.auth.ui.login.LoginActivity
import com.topmovies.mobile.utils.extension.nextActivity
import com.topmovies.mobile.utils.ui.viewBinding

class WelcomeActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityWelcomeBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        splash.setKeepOnScreenCondition { false }
        setInitUi()
    }

    private fun setInitUi() {
        setListeners()
    }

    private fun setListeners() {
        binding.continueButton.setOnClickListener {
            goToLogin()
        }
    }

    private fun goToLogin() {
        nextActivity(
            destination = LoginActivity(),
            animIn = R.anim.slide_in_left,
            animOut = R.anim.slide_out_left,
        )
    }
}
