package com.example.movielocal.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movielocal.common.Consts

@Entity(tableName = Consts.MOVIE_TABLE)
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val movieId: Int?,
    val movieTitle: String = "",
    val description: String = "",
    val rating: Double = 0.0,
    val duration: String = "",
    val genre: String = "",
    val releaseDate: String = "",
    val trailer: String = ""
)
