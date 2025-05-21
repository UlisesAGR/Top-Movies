/*
 * Database.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.topmovies.mobile.data.local.dao.MovieDao
import com.topmovies.mobile.data.local.model.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class Database : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
