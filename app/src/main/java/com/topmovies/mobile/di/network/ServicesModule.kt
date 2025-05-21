/*
 * ServicesModule.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2024. All rights reserved.
 */
package com.topmovies.mobile.di.network

import com.topmovies.mobile.data.netwotk.service.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ServicesModule {

    @Provides
    fun provideMoviesService(retrofit: Retrofit): MoviesService =
        retrofit.create(MoviesService::class.java)
}
