/*
 * LoginActivity.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.presentation.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.topmovies.mobile.databinding.ActivityLoginBinding
import com.topmovies.mobile.util.nextActivityFinish
import com.topmovies.mobile.util.viewBinding

class LoginActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setInitUi()
    }

    private fun setInitUi() {
        setListeners()
    }

    private fun setListeners() {
        binding.sigInButton.setOnClickListener {
            nextActivityFinish(MoviesActivity())
        }
    }
}
