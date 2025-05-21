/*
 * NetworkSourceModule.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.di.source

import com.topmovies.mobile.data.netwotk.source.MoviesNetworkSource
import com.topmovies.mobile.data.netwotk.source.MoviesNetworkSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkSourceModule {

    @Binds
    @Singleton
    abstract fun provideMoviesNetworkSource(moviesNetworkSourceImpl: MoviesNetworkSourceImpl): MoviesNetworkSource
}
