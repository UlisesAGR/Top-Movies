/*
 * ErrorMessageProviderImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.presentation.provider

import android.content.Context
import com.topmovies.mobile.R
import com.topmovies.mobile.utils.logger.log
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class ErrorMessageProviderImpl @Inject constructor(
    @ApplicationContext val context: Context,
): ErrorMessageProvider {

    override fun getUserMessage(cause: Throwable?): String = with(context) {
        log(message = cause.toString())
        return when (cause) {
            is UnknownHostException, is ConnectException, is SocketException -> getString(R.string.check_your_internet_connection)
            is SocketTimeoutException, is TimeoutException -> getString(R.string.timeout)
            is HttpException -> when (cause.code()) {
                404 -> getString(R.string.not_found)
                500 -> getString(R.string.server_error)
                else -> getString(R.string.generic_http_error)
            }
            else -> getString(R.string.please_try_again_later)
        }
    }
}
