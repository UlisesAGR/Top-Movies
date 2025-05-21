/*
 * LocalSourceModule.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.di.source

import com.topmovies.mobile.data.local.source.MoviesLocalSource
import com.topmovies.mobile.data.local.source.MoviesLocalSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalSourceModule {

    @Binds
    @Singleton
    abstract fun provideMoviesLocalSource(moviesLocalSourceImpl: MoviesLocalSourceImpl): MoviesLocalSource
}
