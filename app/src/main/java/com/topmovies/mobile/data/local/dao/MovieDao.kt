/*
 * MovieDao.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.topmovies.mobile.data.local.model.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie_table")
    suspend fun getMovies(): List<MovieEntity>

    @Query("SELECT * FROM movie_table WHERE id = :movieId")
    suspend fun getMovieById(movieId: Int): MovieEntity?
}
