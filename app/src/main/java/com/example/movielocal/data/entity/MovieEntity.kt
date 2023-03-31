package com.example.movielocal.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movielocal.common.Consts

@Entity(tableName = Consts.MOVIE_TABLE)
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movieId")
    val movieId: Int? = null,
    @ColumnInfo(name = "movieTitle")
    val movieTitle: String = "",
    @ColumnInfo(name = "description")
    val description: String = "",
    @ColumnInfo(name = "rating")
    val rating: Double = 0.0,
    @ColumnInfo(name = "duration")
    val duration: String = "",
    @ColumnInfo(name = "genre")
    val genre: String = "",
    @ColumnInfo(name = "releaseDate")
    val releaseDate: String = "",
    @ColumnInfo(name = "trailer")
    val trailer: String = "",
    @ColumnInfo(name = "watchList")
    val watchList: Boolean = false,
    @ColumnInfo(name = "cover")
    val cover: String = ""
)
