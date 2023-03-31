package com.example.movielocal.data.source.local

import androidx.room.*
import com.example.movielocal.common.Consts.MOVIE_TABLE
import com.example.movielocal.data.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Update
    suspend fun updateMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Query("delete from $MOVIE_TABLE")
    suspend fun deleteAllMovie()

    @Query("select * from $MOVIE_TABLE order by movieTitle asc")
    fun getAllMoviesByOrderAsc(): Flow<List<MovieEntity>>?

    @Query("select * from $MOVIE_TABLE order by movieTitle desc")
    fun getAllMoviesByOrderDesc(): Flow<List<MovieEntity>>?

    @Query("select * from $MOVIE_TABLE where movieId = :id")
    fun getMovieById(id: Int): Flow<MovieEntity>?
}