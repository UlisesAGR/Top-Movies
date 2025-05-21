/*
 * ValidationLoginUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.domain.usecase.auth

import com.topmovies.mobile.domain.model.auth.LoginStatus
import com.topmovies.mobile.utils.extension.isValidEmail
import javax.inject.Inject

class ValidationLoginUseCase @Inject constructor() {

    operator fun invoke(
        email: String,
        password: String,
    ): LoginStatus =
        when {
            email.isEmpty() -> LoginStatus.EMPTY_EMAIL
            !email.isValidEmail() -> LoginStatus.INVALID_EMAIL
            password.isEmpty() -> LoginStatus.EMPTY_PASSWORD
            else -> LoginStatus.SUCCESS
        }
}
