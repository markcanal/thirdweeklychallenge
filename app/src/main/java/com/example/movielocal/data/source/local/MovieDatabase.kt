package com.example.movielocal.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movielocal.data.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 2, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}