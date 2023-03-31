package com.example.movielocal.domain.repository

import com.example.movielocal.data.entity.MovieEntity
import com.example.movielocal.data.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun saveMovie(movie: MovieDetails, cover: String?)
    suspend fun deleteMovie(movie: MovieDetails, cover: String?)
    fun getAllMoviesOrder(isDesc: Boolean): Flow<List<MovieEntity>>?
    fun getAllMovies(): Flow<List<MovieEntity>>?
    fun getMoviesById(id: Int): Flow<MovieEntity>?
    suspend fun updateMovie(movieEntity: MovieEntity)
    suspend fun deleteAllMovies()
    suspend fun setMovieFromAsset()
}