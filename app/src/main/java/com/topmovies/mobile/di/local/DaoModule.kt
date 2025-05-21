/*
 * DaoModule.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.di.local

import com.topmovies.mobile.data.local.dao.MovieDao
import com.topmovies.mobile.data.local.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideProductDao(database: Database): MovieDao =
        database.movieDao()
}
