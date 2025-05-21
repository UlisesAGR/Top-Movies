/*
 * MoviesRepositoryModule.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.di.repository

import com.topmovies.mobile.data.repository.MoviesRepositoryImpl
import com.topmovies.mobile.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MoviesRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideMoviesRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository
}
