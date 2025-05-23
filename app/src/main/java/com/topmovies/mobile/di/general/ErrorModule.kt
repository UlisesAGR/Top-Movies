/*
 * ErrorModule.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.di.general

import android.content.Context
import com.topmovies.mobile.presentation.provider.ErrorMessageProvider
import com.topmovies.mobile.presentation.provider.ErrorMessageProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ErrorModule {

    @Provides
    fun provideErrorMessageProvider(
        @ApplicationContext context: Context,
    ): ErrorMessageProvider = ErrorMessageProviderImpl(context)
}
