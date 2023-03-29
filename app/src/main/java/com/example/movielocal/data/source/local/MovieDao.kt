package com.example.movielocal.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movielocal.common.Consts.MOVIE_TABLE
import com.example.movielocal.data.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieEntity)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Delete
    fun deleteMovie(movie: MovieEntity)

    @Query("delete from $MOVIE_TABLE")
    fun deleteAllMovie()

    @Query("select * from $MOVIE_TABLE order by movieTitle asc")
    fun getAllMoviesByOrderAsc(): LiveData<List<MovieEntity>>

    @Query("select * from $MOVIE_TABLE order by movieTitle desc")
    fun getAllMoviesByOrderDesc(): LiveData<List<MovieEntity>>
}